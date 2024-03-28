package dev.upaya.shf.data.session_history

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test


class SessionHistoryRepositoryTest {

    @Test
    fun startRecordingEvents() = runTest {

        val job = Job()
        val scope = CoroutineScope(job)

    }
}
