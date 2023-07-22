package dev.upaya.shf.ui.session.feelings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import dev.upaya.shf.exercises.feelings.CoreFeelingsSessionRepository
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_events.InputEvent
import dev.upaya.shf.inputs.input_events.InputEventSource
import dev.upaya.shf.inputs.input_keys.InputKey
import dev.upaya.shf.inputs.input_keys.ForegroundKeySource
import dev.upaya.shf.inputs.input_keys.GlobalInputKeySource
import dev.upaya.shf.inputs.input_keys.IInputKeyRegistrar
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CoreFeelingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    exerciseRepository: ExerciseRepository,
    inputEventSource: InputEventSource,
    inputKeySource: GlobalInputKeySource,
    private val coreFeelingsSessionRepository: CoreFeelingsSessionRepository,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = inputEventSource.inputEvent
    private var inputKeyFlow: SharedFlow<InputKey> = inputKeySource.inputKeyDown

    private val exerciseId = ExerciseId.valueOf(checkNotNull(savedStateHandle[dev.upaya.shf.ui.session.noting.routeArgExerciseId]) as String)
    private val labelMap = checkNotNull(exerciseRepository.getExerciseConfig(exerciseId)).labelMap

    internal var labelFlow: SharedFlow<Label> = inputKeyFlow.transformToLabel(labelMap = labelMap, scope = viewModelScope)

    val currentCoreFeeling: StateFlow<String?> = coreFeelingsSessionRepository.currentFeeling
    val resultList = coreFeelingsSessionRepository.resultList
    val round: StateFlow<Int> = coreFeelingsSessionRepository.round

    fun keepCurrentFeeling() {
        coreFeelingsSessionRepository.keepCurrentFeeling()
    }

    fun discardCurrentFeeling() {
        coreFeelingsSessionRepository.discardCurrentFeeling()
    }

}
