package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Not, a background session here does not necessarily mean that the session is actually running in
 * background right now. It means that the setting for background sessions is enabled.
 */
enum class SessionState {
    NOT_RUNNING, FOREGROUND_SESSION_RUNNING, BACKGROUND_SESSION_RUNNING
}


@Singleton
class SessionStateDataSource @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val _sessionState = MutableStateFlow(SessionState.NOT_RUNNING)
    val sessionState: StateFlow<SessionState> = _sessionState

    suspend fun startSession() {
        val background = withContext(ioDispatcher) {
            preferencesRepository.isLockScreenSessionEnabled.first()
        }
        _sessionState.value = if (background) SessionState.BACKGROUND_SESSION_RUNNING else SessionState.FOREGROUND_SESSION_RUNNING
    }

    fun stopSession() {
        _sessionState.value = SessionState.NOT_RUNNING
    }

}
