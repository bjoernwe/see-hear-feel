package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.dataclasses.Session
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import org.junit.Assert
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


class SessionStatsRepositoryTest {

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
        Assert.assertEquals(1L, duration)
    }
}


fun createSession(sessionStart: Instant, init: SessionFactoryScope.() -> Unit): SessionWithEvents {
    val sessionFactoryScope = SessionFactoryScope()
    sessionFactoryScope.init()
    return SessionWithEvents(
        session = Session(start = sessionStart, end = sessionFactoryScope.sessionEnd),
        notings = sessionFactoryScope.notings,
        delays = sessionFactoryScope.delays,
    )
}


class SessionFactoryScope {
    var sessionEnd: Instant? = null
    val notings: MutableList<SHFLabelEvent> = mutableListOf()
    val delays: MutableList<InputDelayEvent> = mutableListOf()

    fun note(timestamp: Instant, label: SHFLabel) {
        notings.add(SHFLabelEvent(timestamp = timestamp, label = label))
    }

    fun delay(timestamp: Instant) {
        delays.add(InputDelayEvent(
            timestamp = timestamp,
            delayInterval = 0,  // could further be simplified by calculating this automatically
            delaysInARow = 1,   // could further be simplified by calculating this automatically
            )
        )
    }

    fun endSession(timestamp: Instant) {
        sessionEnd = timestamp
    }
}


private fun timestamp(
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): Instant {
    return LocalDateTime.of(year, month, day, hour, minute, second).toInstant(ZoneOffset.UTC)
}
