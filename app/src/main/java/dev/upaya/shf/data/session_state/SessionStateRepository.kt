package dev.upaya.shf.data.session_state

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStateRepository @Inject constructor(
    private val sessionStateDataSource: SessionStateDataSource,
) {

    fun startSession() {
        sessionStateDataSource.startSession()
    }

    fun stopSession() {
        sessionStateDataSource.stopSession()
    }

}
