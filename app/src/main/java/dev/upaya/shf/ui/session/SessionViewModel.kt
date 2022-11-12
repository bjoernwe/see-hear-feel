package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import dev.upaya.shf.session.ActiveSessionSource
import dev.upaya.shf.ui.input.InputEventStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val inputEventSource: InputEventSource,
    private val activeSessionSource: ActiveSessionSource,
    private val inputEventStats: InputEventStats,
) : ViewModel() {

    var label: Flow<Label> = inputEventSource.inputEvent.transformToLabel()
    var labelFreqs = inputEventStats.labelFreqs

    init {
        initStatsCollection()
    }

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

    private fun initStatsCollection() {
        viewModelScope.launch {
            label.collect { label ->
                inputEventStats.reportInputEvent(label)
            }
        }
    }

}
