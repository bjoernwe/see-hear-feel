package dev.upaya.shf.ui.session.noting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_events.InputEvent
import dev.upaya.shf.inputs.input_events.InputEventSource
import dev.upaya.shf.inputs.input_events.InputEventStats
import dev.upaya.shf.inputs.input_events.LabelFreqs
import dev.upaya.shf.inputs.input_keys.InputKey
import dev.upaya.shf.inputs.input_keys.GlobalInputKeySource
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    exerciseRepository: ExerciseRepository,
    inputEventSource: InputEventSource,
    inputKeySource: GlobalInputKeySource,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = inputEventSource.inputEvent
    private var inputKeyFlow: SharedFlow<InputKey> = inputKeySource.inputKeyDown

    private val exerciseId = ExerciseId.valueOf(checkNotNull(savedStateHandle[routeArgExerciseId]) as String)
    private val labelMap = checkNotNull(exerciseRepository.getExerciseConfig(exerciseId)).labelMap
    private val inputEventStats = InputEventStats(
        inputEventFlow = inputEventFlow,
        labelMap = labelMap,
        coroutineScope = viewModelScope,
    )

    internal var labelFlow: SharedFlow<Label> = inputKeyFlow.transformToLabel(labelMap = labelMap, scope = viewModelScope)

    val usingBackgroundSource: StateFlow<Boolean> = inputKeySource.usingBackgroundSource

    fun getNumEvents(): Int {
        return inputEventStats.inputEvents.size
    }

    fun getSessionLength(): Int? {
        return inputEventStats.sessionTime
    }

    fun getLabelFreqs(): LabelFreqs {
        return inputEventStats.labelFreqs
    }

    internal fun startStatsCollection() {
        inputEventStats.start()
    }

    internal fun stopStatsCollection() {
        inputEventStats.stop()
    }

}
