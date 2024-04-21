package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.dataclasses.Session
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import org.junit.Assert
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset


class SessionStatsRepositoryTest {

    @Test
    fun sessionStatsRepository_calcSessionDuration_calcDurationFromSessionTimestamps() {

        // GIVEN a session with events
        val session = SessionWithEvents(
            session = Session(
                start = offsetDate(second = 0),
                end = null,
                ),
            notings = listOf(
                labelEvent(second = 2),  // start counting here
                labelEvent(second = 3),  // stop counting here
            ),
            delays = listOf(
                delayEvent(second = 1),  // ignore before first noting
                delayEvent(second = 4),  // ignore after last noting
            ),
        )

        // WHEN the duration is calculated
        val duration = calcSessionDuration(session = session)

        // THEN it is based on the noting events and not the session timestamps, that is, what
        // counts is actual active noting time, ignoring delays in the beginning and end
        Assert.assertEquals(1L, duration)
    }
}


private fun labelEvent(
    label: SHFLabel = SHFLabel.SEE,
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): SHFLabelEvent {
    return SHFLabelEvent(
        label = label,
        timestamp = offsetDate(
            year = year,
            month = month,
            day = day,
            hour = hour,
            minute = minute,
            second = second,
        )
    )
}


private fun delayEvent(
    delaysInARow: Int = 0,
    delayInterval: Int = 0,
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): InputDelayEvent {
    return InputDelayEvent(
        delaysInARow = delaysInARow,
        delayInterval = delayInterval,
        timestamp = offsetDate(
            year = year,
            month = month,
            day = day,
            hour = hour,
            minute = minute,
            second = second,
        )
    )
}


private fun offsetDate(
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): OffsetDateTime {
    return OffsetDateTime.of(year, month, day, hour, minute, second, 0, ZoneOffset.UTC)
}
