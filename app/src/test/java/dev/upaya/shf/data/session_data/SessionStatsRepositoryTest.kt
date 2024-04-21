package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.dataclasses.NotingEvent
import dev.upaya.shf.data.session_data.dataclasses.Session
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import dev.upaya.shf.data.session_data.datastore.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionWithEventsEntry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.OffsetDateTime


class SessionStatsRepositoryTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun sessionStatsRepository_calcSessionDuration_calcDurationFromSessionTimestamps() {

        // GIVEN a session with events
        val session = SessionWithEvents(
            session = Session(
                id = 0,
                start = OffsetDateTime.parse("2000-01-01T00:00:00+00:00"),
                end = null,
                ),
            notings = listOf(
                NotingEvent(date = OffsetDateTime.parse("2000-01-01T00:01:00+00:00"), label = SHFLabel.SEE),
                NotingEvent(date = OffsetDateTime.parse("2000-01-01T00:01:05+00:00"), label = SHFLabel.SEE),
                NotingEvent(date = OffsetDateTime.parse("2000-01-01T00:01:15+00:00"), label = SHFLabel.SEE),
            ),
            delays = listOf(
                InputDelayEvent(delaysInARow = 0, delayInterval = 1, timestamp = OffsetDateTime.parse("2000-01-01T00:01:10+00:00")),
                InputDelayEvent(delaysInARow = 0, delayInterval = 1, timestamp = OffsetDateTime.parse("2000-01-01T00:01:20+00:00")),
                InputDelayEvent(delaysInARow = 0, delayInterval = 1, timestamp = OffsetDateTime.parse("2000-01-01T00:01:30+00:00")),
            ),
        )

        // WHEN the duration is calculated
        val duration = calcSessionDuration(session = session)

        // THEN it is based on the noting events and not the session timestamps, that is, what
        // counts is actual active noting time, ignoring delays in the beginngin and end
        Assert.assertEquals(15L, duration)
    }
}
