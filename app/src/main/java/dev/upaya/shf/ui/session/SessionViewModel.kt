package dev.upaya.shf.ui.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import dev.upaya.shf.ui.session.input.InputEventStats
import dev.upaya.shf.ui.session.input.LabelFreqs
import dev.upaya.shf.ui.session.noting.routeArgExerciseId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    exerciseRepository: ExerciseRepository,
    private val inputEventSource: InputEventSource,
) : ViewModel() {

    internal var label: Flow<Label> = inputEventSource.inputEvent.transformToLabel()

    private val exerciseId = ExerciseID.valueOf(checkNotNull(savedStateHandle[routeArgExerciseId]) as String)
    private val labelMap = checkNotNull(exerciseRepository.getExerciseConfig(exerciseId)).labelMap
    private val inputEventStats = InputEventStats(labelMap = labelMap)

    init {
        initStatsCollection()
    }

    fun getNumEvents(): Int {
        return inputEventStats.inputEvents.size
    }

    fun getSessionLength(): Int? {
        return inputEventStats.sessionTime
    }

    fun getLabelFreqs(): LabelFreqs {
        return inputEventStats.labelFreqs
    }

    private fun StateFlow<InputEvent?>.transformToLabel(): Flow<Label> {
        return this.transform { inputEvent ->
            if (inputEvent != null) {
                emit(labelMap.getLabel(inputEvent.inputKey))
            }
        }
    }

    private fun initStatsCollection() {
        viewModelScope.launch {
            inputEventSource.inputEvent.collect { inputEventOrNull ->
                inputEventOrNull?.let { inputEvent ->
                    inputEventStats.reportInputEvent(inputEvent)
                }
            }
        }
    }

}
