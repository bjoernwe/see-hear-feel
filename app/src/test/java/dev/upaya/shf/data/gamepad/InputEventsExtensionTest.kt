package dev.upaya.shf.data.gamepad

import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.labels.calcDuration
import org.junit.Assert.*

import org.junit.Test
import java.time.Instant
import java.time.OffsetDateTime

class InputEventsExtensionTest {

    @Test
    fun calcSessionLength() {

        // GIVEN an ordered list of events
        val events = listOf(
            SHFLabelEvent(timestamp = Instant.parse("2000-12-31T00:00:00Z"), label = SHFLabel.GONE),
            SHFLabelEvent(timestamp = Instant.parse("2000-12-31T00:00:01Z"), label = SHFLabel.GONE),
            SHFLabelEvent(timestamp = Instant.parse("2000-12-31T00:00:02Z"), label = SHFLabel.GONE),
        )

        // WHEN their duration (first to last) is calculated
        val sessionLength = events.calcDuration()

        // THEN it is calculated correctly
        assertEquals(2, sessionLength)
    }
}
