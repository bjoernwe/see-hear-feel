package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.user_interaction.UserInteractionRepository
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_history.SessionHistoryRepository
import dev.upaya.shf.data.session_history.datastore.SessionResource
import dev.upaya.shf.data.session_stats.SessionStatsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionStatsRepository: SessionStatsRepository,
    private val sessionHistoryRepository: SessionHistoryRepository,
    private val userInteractionRepository: UserInteractionRepository,
    shfLabelDataSource: SHFLabelDataSource,
) : ViewModel() {

    internal val labelFlow: Flow<SHFLabelEvent> = shfLabelDataSource.labelFlow
    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    private lateinit var sessionResource: SessionResource

    init {
        viewModelScope.launch {
            sessionResource = sessionHistoryRepository.createSessionResource()
            addCloseable(sessionResource)  // close resource when session/view model is closed
            onSessionStart()
        }
    }

    /*
    This is meant as the authoritative place where a session starts. It's also ended here through
    the viewModelScope, which closes when the user navigates away from the session screen.
     */
    private fun onSessionStart() {
        sessionStatsRepository.startStatsCollection(scope = viewModelScope)
        sessionHistoryRepository.addLabelEventListener(scope = viewModelScope, onLabelEvent = ::storeNotingEvent)
        sessionHistoryRepository.addInputDelayListener(scope = viewModelScope, onInputDelay = ::storeInputDelayEvent)
        sessionHistoryRepository.addInputDelayListener(scope = viewModelScope) { userInteractionRepository.vibrate() }
    }

    private fun storeNotingEvent(labelEvent: SHFLabelEvent) {
        viewModelScope.launch {
            sessionHistoryRepository.storeNotingEvent(labelEvent = labelEvent, sessionId = sessionResource.sessionId)
        }
    }

    private fun storeInputDelayEvent(inputDelayEvent: InputDelayEvent) {
        viewModelScope.launch {
            sessionHistoryRepository.storeInputDelayEvent(inputDelayEvent = inputDelayEvent, sessionId = sessionResource.sessionId)
        }
    }
}
