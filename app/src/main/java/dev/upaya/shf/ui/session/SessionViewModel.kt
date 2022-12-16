package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import dev.upaya.shf.session.ActiveSessionSource
import dev.upaya.shf.ui.input.InputEventStats
import dev.upaya.shf.ui.input.LabelFreqs
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

    init {
        initStatsCollection()
    }

    fun startSession(exerciseID: ExerciseID) {
        resetSession()
        activeSessionSource.beginSession(exerciseID = exerciseID)
    }

    fun stopSession() {
        activeSessionSource.endSession()
    }

    private fun resetSession() {
        inputEventSource.resetInputEvent()
        inputEventStats.resetStats()
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
                activeSessionSource.exerciseConfig.value?.labelMap?.let {
                        labelMap -> emit(labelMap.getLabel(inputEvent.inputKey))
                }
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
