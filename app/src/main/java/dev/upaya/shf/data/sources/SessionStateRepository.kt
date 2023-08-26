package dev.upaya.shf.data.sources

import javax.inject.Inject


class SessionStateRepository @Inject constructor(
    private val sessionStateSource: SessionStateSource,
) {

    val isSessionRunning = sessionStateSource.isSessionRunning
    val isBackgroundSession = sessionStateSource.isBackgroundSession

    fun startSession(background: Boolean) {
        sessionStateSource.startSession(background = background)
    }

    fun stopSession() {
        sessionStateSource.stopSession()
    }

}
