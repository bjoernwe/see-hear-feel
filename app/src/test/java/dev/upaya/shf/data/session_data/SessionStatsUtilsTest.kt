package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.labels.SHFLabel
import org.junit.Assert.*

import org.junit.Test


class SessionStatsUtilsKtTest {

    @Test
    fun sessionStatsRepository_calcSessionDuration_calcDurationFromSessionTimestamps() {

        // GIVEN a session with events
        val session = createSession(sessionStart = timestamp(second = 0)) {
            delay(timestamp(second = 1))  // time before first noting should be ignored
            note(timestamp(second = 2), SHFLabel.SEE)  // start counting here
            note(timestamp(second = 3), SHFLabel.SEE)  // stop counting here
            delay(timestamp(second = 4))  // time after last noting should be ignored
            endSession(timestamp(second = 10))
        }

        // WHEN the duration is calculated
        val duration = calcSessionDuration(session = session)

        // THEN it is based on the noting events and not the session timestamps, that is, what
        // counts is actual active noting time, ignoring delays in the beginning and end
        assertEquals(1L, duration)
    }

    @Test
    fun calcMindWandering() {

        // GIVEN a session with events
        val session = createSession(sessionStart = timestamp(second = 0)) {
            note(timestamp(second = 1))     // start of active session
            delay(timestamp(second = 2))    // 50% mind wandering
            note(timestamp(second = 4))     // end of active session
            delay(timestamp(second = 5))    // should be ignored because it is the last event
            endSession(timestamp(second = 5))
        }

        // WHEN the amount of mind wandering is calculated
        val amountOfMindWandering = calcMindWandering(session = session)

        // THEN it is based on the first and last noting
        assertEquals(.33f, amountOfMindWandering, .01f)
    }
}


