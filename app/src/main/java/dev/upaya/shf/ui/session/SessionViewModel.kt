package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import dev.upaya.shf.session.ActiveSessionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val inputEventSource: InputEventSource,
    private val activeSessionSource: ActiveSessionSource,
) : ViewModel() {

    var label: Flow<Label> = inputEventSource.inputEvent.transformToLabel()

    fun beginSession(exerciseConfig: ExerciseConfig) {
        activeSessionSource.beginSession(exerciseConfig = exerciseConfig)
        inputEventSource.resetInputEvent()
    }

    fun endSession() {
        activeSessionSource.endSession()
    }

    private fun StateFlow<InputEvent?>.transformToLabel(): Flow<Label> {
        return this.transform { inputEvent ->
            if (inputEvent != null) {
                activeSessionSource.exerciseConfig.value?.labelMap?.let {
                        labelMap -> emit(labelMap.getLabel(inputEvent.inputKey))
                }
            }
        }
    }

}
