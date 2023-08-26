package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.ui.LabelMapSHF
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.InputEventStats
import dev.upaya.shf.data.sources.LabelFreqs
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.KeyPressRepository
import dev.upaya.shf.data.sources.SessionStateRepository
import dev.upaya.shf.ui.asSharedFlow
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val keyPressRepository: KeyPressRepository,
    private val sessionStateRepository: SessionStateRepository,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = keyPressRepository.inputEvent.asSharedFlow(viewModelScope)
    private var inputKeyFlow: SharedFlow<InputKey> = keyPressRepository.keyDown.asSharedFlow(viewModelScope)

    val isBackgroundSession: StateFlow<Boolean> = sessionStateRepository.isBackgroundSession

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
        sessionStateRepository.startSession(background = isBackgroundSession.value)
        inputEventStats.start()
        keyPressRepository.enableKeyCapturing(true)
    }

    internal fun stopSession() {
        keyPressRepository.enableKeyCapturing(false)
        inputEventStats.stop()
        sessionStateRepository.stopSession()
    }

}
