package dev.upaya.shf.data.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


enum class SessionState {
    NOT_RUNNING, RUNNING_IN_FOREGROUND, RUNNING_IN_BACKGROUND
}


@Singleton
class SessionStateDataSource @Inject constructor() {

    private val _sessionState = MutableStateFlow(SessionState.NOT_RUNNING)
    val sessionState: StateFlow<SessionState> = _sessionState

    fun startSession(background: Boolean) {
        _sessionState.value = if (background) SessionState.RUNNING_IN_BACKGROUND else SessionState.RUNNING_IN_FOREGROUND
    }

    fun stopSession() {
        _sessionState.value = SessionState.NOT_RUNNING
    }

}
