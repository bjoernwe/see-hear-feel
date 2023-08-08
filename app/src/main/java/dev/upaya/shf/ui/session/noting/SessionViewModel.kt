package dev.upaya.shf.ui.session.noting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.labelmaps.LabelMapSHF
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.SessionStateSource
import dev.upaya.shf.inputs.events.InputEvent
import dev.upaya.shf.inputs.events.InputEventSource
import dev.upaya.shf.inputs.events.InputEventStats
import dev.upaya.shf.inputs.events.LabelFreqs
import dev.upaya.shf.inputs.keys.InputKey
import dev.upaya.shf.inputs.keys.GlobalInputKeySource
import dev.upaya.shf.inputs.keys.GlobalInputRegistrarSwitch
import dev.upaya.shf.ui.asSharedFlow
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    inputEventSource: InputEventSource,
    inputKeySource: GlobalInputKeySource,
    private val sessionStateSource: SessionStateSource,
    private val globalInputRegistrarSwitch: GlobalInputRegistrarSwitch,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = inputEventSource.inputEvent.asSharedFlow(viewModelScope)
    private var inputKeyFlow: SharedFlow<InputKey> = inputKeySource.inputKeyDown.asSharedFlow(viewModelScope)

    private val labelMap = LabelMapSHF
    private val inputEventStats = InputEventStats(
        inputEventFlow = inputEventFlow,
        labelMap = labelMap,
        coroutineScope = viewModelScope,
    )

    internal var labelFlow: SharedFlow<Label> = inputKeyFlow.transformToLabel(labelMap = labelMap, scope = viewModelScope)

    fun getNumEvents(): Int {
        return inputEventStats.inputEvents.size
    }

    fun getSessionLength(): Int? {
        return inputEventStats.sessionTime
    }

    fun getLabelFreqs(): LabelFreqs {
        return inputEventStats.labelFreqs
    }

    internal fun startSession() {
        sessionStateSource.startSession()
        inputEventStats.start()
        globalInputRegistrarSwitch.switchOn()
    }

    internal fun stopSession() {
        globalInputRegistrarSwitch.switchOff()
        inputEventStats.stop()
        sessionStateSource.stopSession()
    }

}
