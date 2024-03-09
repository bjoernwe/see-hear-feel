package dev.upaya.shf.data.sources

import javax.inject.Inject


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
