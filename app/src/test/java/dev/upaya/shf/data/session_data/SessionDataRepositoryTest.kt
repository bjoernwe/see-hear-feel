package dev.upaya.shf.data.session_data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest

import org.junit.Test


class SessionDataRepositoryTest {

    @Test
    fun startRecordingEvents() = runTest {

        val job = Job()
        val scope = CoroutineScope(job)

    }
}
