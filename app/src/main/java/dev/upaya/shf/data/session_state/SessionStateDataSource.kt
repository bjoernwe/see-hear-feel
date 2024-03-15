package dev.upaya.shf.data.session_state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


enum class SessionState {
    NOT_RUNNING, FOREGROUND_SESSION_RUNNING,
}


@Singleton
class SessionStateDataSource @Inject constructor() {

    private val _sessionState = MutableStateFlow(SessionState.NOT_RUNNING)
    val sessionState: StateFlow<SessionState> = _sessionState

    fun startSession() {
        _sessionState.value = SessionState.FOREGROUND_SESSION_RUNNING
    }

    fun stopSession() {
        _sessionState.value = SessionState.NOT_RUNNING
    }

}
