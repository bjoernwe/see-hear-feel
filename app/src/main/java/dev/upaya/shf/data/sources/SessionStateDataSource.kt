package dev.upaya.shf.data.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStateDataSource @Inject constructor() {

    private val _isSessionRunning = MutableStateFlow(false)
    val isSessionRunning: StateFlow<Boolean> = _isSessionRunning

    private val _isBackgroundSession = MutableStateFlow(false)
    val isBackgroundSession: StateFlow<Boolean> = _isBackgroundSession

    fun startSession(background: Boolean) {
        _isBackgroundSession.value = background
        _isSessionRunning.value = true
    }

    fun stopSession() {
        _isSessionRunning.value = false
    }

}
