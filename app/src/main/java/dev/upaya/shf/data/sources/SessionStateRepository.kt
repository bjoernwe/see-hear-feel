package dev.upaya.shf.data.sources

import javax.inject.Inject


class SessionStateRepository @Inject constructor(
    private val sessionStateDataSource: SessionStateDataSource,
) {

    //val sessionState = sessionStateDataSource.sessionState

    fun startSession(background: Boolean) {
        sessionStateDataSource.startSession(background = background)
    }

    fun stopSession() {
        sessionStateDataSource.stopSession()
    }

}
