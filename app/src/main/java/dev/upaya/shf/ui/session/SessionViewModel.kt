package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.app.AnalyticsLogger
import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.user_interaction.UserInteractionRepository
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.SessionDataRepository
import dev.upaya.shf.data.session_data.datastore.SessionResource
import dev.upaya.shf.data.session_data.SessionStatsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionDataRepository: SessionDataRepository,
    private val userInteractionRepository: UserInteractionRepository,
    private val analyticsLogger: AnalyticsLogger,
    sessionStatsRepository: SessionStatsRepository,
    shfLabelDataSource: SHFLabelDataSource,
) : ViewModel() {

    internal val labelFlow: Flow<SHFLabelEvent> = shfLabelDataSource.labelFlow
    val numEvents: Flow<Int> = sessionStatsRepository.numEvents

    private lateinit var sessionResource: SessionResource

    init {
        viewModelScope.launch {
            sessionResource = sessionDataRepository.createSessionResource()
            addCloseable(sessionResource)  // close resource when session/view model is closed
            onSessionStart()
        }
    }

    /*
    This is meant as the authoritative place where a session starts. It's also ended here through
    the viewModelScope, which closes when the user navigates away from the session screen.
     */
    private fun onSessionStart() {
        sessionDataRepository.addLabelEventListener(scope = viewModelScope, onLabelEvent = ::storeNotingEvent)
        sessionDataRepository.addLabelEventListener(scope = viewModelScope) { analyticsLogger.logNotingEvent(it.label) }
        sessionDataRepository.addInputDelayListener(scope = viewModelScope, onInputDelay = ::storeInputDelayEvent)
        sessionDataRepository.addInputDelayListener(scope = viewModelScope) { userInteractionRepository.vibrate() }
        analyticsLogger.logSessionStart()
    }

    private fun storeNotingEvent(labelEvent: SHFLabelEvent) {
        viewModelScope.launch {
            sessionDataRepository.storeNotingEvent(labelEvent = labelEvent, sessionId = sessionResource.sessionId)
        }
    }

    private fun storeInputDelayEvent(inputDelayEvent: InputDelayEvent) {
        viewModelScope.launch {
            sessionDataRepository.storeInputDelayEvent(inputDelayEvent = inputDelayEvent, sessionId = sessionResource.sessionId)
        }
    }
}
