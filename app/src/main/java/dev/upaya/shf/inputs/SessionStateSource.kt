package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStateSource @Inject constructor() {

    private val _isSessionRunning = MutableStateFlow(false)
    val isSessionRunning: StateFlow<Boolean> = _isSessionRunning

    fun startSession() {
        _isSessionRunning.value = true
    }

    fun stopSession() {
        _isSessionRunning.value = false
    }

}
