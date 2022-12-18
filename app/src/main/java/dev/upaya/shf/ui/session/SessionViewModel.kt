package dev.upaya.shf.ui.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import dev.upaya.shf.ui.asSharedFlow
import dev.upaya.shf.ui.session.noting.routeArgExerciseId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    exerciseRepository: ExerciseRepository,
    inputEventSource: InputEventSource,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = inputEventSource.inputEvent.asSharedFlow(scope = viewModelScope)
    internal var labelFlow: SharedFlow<Label> = inputEventFlow.transformToLabel(scope = viewModelScope)

    private val exerciseId = ExerciseId.valueOf(checkNotNull(savedStateHandle[routeArgExerciseId]) as String)
    private val labelMap = checkNotNull(exerciseRepository.getExerciseConfig(exerciseId)).labelMap
    private val inputEventStats = InputEventStats(
        inputEventFlow = inputEventFlow,
        labelMap = labelMap,
        coroutineScope = viewModelScope,
    )

    fun getNumEvents(): Int {
        return inputEventStats.inputEvents.size
    }

    fun getSessionLength(): Int? {
        return inputEventStats.sessionTime
    }

    fun getLabelFreqs(): LabelFreqs {
        return inputEventStats.labelFreqs
    }

    private fun SharedFlow<InputEvent>.transformToLabel(scope: CoroutineScope): SharedFlow<Label> {
        return this.transform { inputEvent ->
            emit(labelMap.getLabel(inputEvent.inputKey))
        }.shareIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            replay = 0,
        )
    }

    internal fun startStatsCollection() {
        inputEventStats.start()
    }

    internal fun stopStatsCollection() {
        inputEventStats.stop()
    }

}
