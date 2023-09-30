package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.ui.LabelMapSHF
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.LabelFreqs
import dev.upaya.shf.data.UserInteractionRepository
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
    userInteractionRepository: UserInteractionRepository,
    private val sessionStateRepository: SessionStateRepository,
    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {

    internal val inputEventFlow: SharedFlow<InputEvent> = userInteractionRepository.inputEvent.asSharedFlow(viewModelScope)
    
    // TODO: Move labels to repository. Initially they've been kept out they could be seen as part of the presentation layer. But...

    internal val labelFlow: SharedFlow<Label> = userInteractionRepository.keyDown.transformToLabel(labelMap = LabelMapSHF, scope = viewModelScope)
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

    suspend fun startSession() {
        val background = withContext(ioDispatcher) {
            preferencesRepository.isLockScreenSessionEnabled.first()
        }
        sessionStateRepository.startSession(background = background)
        sessionStatsRepository.startStatsCollection(coroutineScope = viewModelScope, inputEventFlow = inputEventFlow)
    }

    internal fun stopSession() {
        sessionStatsRepository.stopStatsCollection()
        sessionStateRepository.stopSession()
    }
}
