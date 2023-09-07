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
import dev.upaya.shf.data.sources.IoDispatcher
import dev.upaya.shf.data.sources.PreferencesRepository
import dev.upaya.shf.data.sources.SessionStateRepository
import dev.upaya.shf.data.sources.SessionStatsRepository
import dev.upaya.shf.ui.asSharedFlow
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val keyPressRepository: KeyPressRepository,
    private val sessionStateRepository: SessionStateRepository,
    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {

    private val inputKeyFlow: SharedFlow<InputKey> = keyPressRepository.keyDown
    internal val inputEventFlow: SharedFlow<InputEvent> = keyPressRepository.inputEvent.asSharedFlow(viewModelScope)
    internal val labelFlow: SharedFlow<Label> = inputKeyFlow.transformToLabel(labelMap = LabelMapSHF, scope = viewModelScope)
    internal val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    fun getNumEvents(): Int {
        return sessionStatsRepository.getNumEvents()
    }

    fun getSessionLength(): Int? {
        return sessionStatsRepository.getSessionLength()
    }

    fun getLabelFreqs(): LabelFreqs {
        return sessionStatsRepository.getLabelFreqs()
    }

    suspend fun startSession(onStartSession: (Boolean) -> Unit) {
        val background = withContext(ioDispatcher) {
            preferencesRepository.isLockScreenSessionEnabled.first()
        }
        sessionStateRepository.startSession(background = background)
        onStartSession(background)
        sessionStatsRepository.startStatsCollection(coroutineScope = viewModelScope, inputEventFlow = inputEventFlow)
        keyPressRepository.enableKeyCapturing(true)
    }

    internal fun stopSession(onStopSession: () -> Unit) {
        keyPressRepository.enableKeyCapturing(false)
        sessionStatsRepository.stopStatsCollection()
        onStopSession()
        sessionStateRepository.stopSession()
    }
}
