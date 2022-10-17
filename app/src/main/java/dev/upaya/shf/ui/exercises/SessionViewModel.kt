package dev.upaya.shf.ui.exercises

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.inputs.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val inputEventSource: InputEventSource,
) : ViewModel() {

    private var _currentExercise: MutableStateFlow<ExerciseConfig?> = MutableStateFlow(null)
    val inputEvent: StateFlow<InputEvent?> = inputEventSource.inputEvent
    var label = inputEvent.transform { inputEvent ->
        if (inputEvent != null) {
            _currentExercise.value?.labelMap?.let {
                labelMap -> emit(labelMap.getLabel(inputEvent.inputKey))
            }
        }
    }

    fun setCurrentExercise(exerciseConfig: ExerciseConfig) {
        inputEventSource.resetInputEvent()
        _currentExercise.value = exerciseConfig
    }

}
