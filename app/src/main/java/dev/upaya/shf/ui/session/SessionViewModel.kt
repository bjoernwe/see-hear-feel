package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.ui.LabelMapSHF
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.LabelFreqs
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.KeyPressRepository
import dev.upaya.shf.data.sources.InputEventStats
import dev.upaya.shf.data.sources.IoDispatcher
import dev.upaya.shf.data.sources.PreferencesRepository
import dev.upaya.shf.data.sources.SessionStateRepository
import dev.upaya.shf.ui.asSharedFlow
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val keyPressRepository: KeyPressRepository,
    private val sessionStateRepository: SessionStateRepository,
    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    internal var inputEventFlow: SharedFlow<InputEvent> = keyPressRepository.inputEvent.asSharedFlow(viewModelScope)
    private var inputKeyFlow: SharedFlow<InputKey> = keyPressRepository.keyDown.asSharedFlow(viewModelScope)

    private val labelMap = LabelMapSHF
    // TODO: Move to a data layer
    // Here, the view model handles the session state including stats. Instead, the session's state
    // should be handled in a central place like the data source.
    private val inputEventStats = InputEventStats(
        ioDispatcher = Dispatchers.IO,
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

    suspend fun startSession(onStartSession: (Boolean) -> Unit) {
        val background = withContext(ioDispatcher) {
            preferencesRepository.isLockScreenSessionEnabled.first()
        }
        sessionStateRepository.startSession(background = background)
        onStartSession(background)
        inputEventStats.startStatsCollection(coroutineScope = viewModelScope, inputEventFlow = inputEventFlow)
        keyPressRepository.enableKeyCapturing(true)
    }

    internal fun stopSession(onStopSession: () -> Unit) {
        keyPressRepository.enableKeyCapturing(false)
        inputEventStats.stopStatsCollection()
        onStopSession()
        sessionStateRepository.stopSession()
    }

}
