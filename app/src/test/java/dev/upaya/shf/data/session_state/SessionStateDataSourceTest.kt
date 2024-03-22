package dev.upaya.shf.data.session_state

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SessionStateDataSourceTest {

    private lateinit var unitUnderTest: SessionStateDataSource

    @Before
    fun setUp() {
        unitUnderTest = SessionStateDataSource()
    }

    @Test
    fun sessionStateDataSource_startAndStop_areIdempotent() = runTest {

        // GIVEN a SessionStateDataSource
        val consumedValues = mutableListOf<SessionState>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            unitUnderTest.sessionState.toList(consumedValues)
        }

        // WHEN the session is started/stopped multiple times
        unitUnderTest.startSession()
        unitUnderTest.startSession()
        unitUnderTest.stopSession()
        unitUnderTest.stopSession()

        // THEN the redundant input events are ignored
        assertEquals(3, consumedValues.size)
        assertEquals(SessionState.NOT_RUNNING, consumedValues[0])
        assertEquals(SessionState.FOREGROUND_SESSION_RUNNING, consumedValues[1])
        assertEquals(SessionState.NOT_RUNNING, consumedValues[2])
    }
}
