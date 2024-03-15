package dev.upaya.shf.data.gamepad_input

import org.junit.Assert.*

import org.junit.Test
import java.time.Instant

class InputEventsExtensionTest {

    @Test
    fun calcSessionLength() {

        // GIVEN an ordered list of events
        val events = listOf(
            GamepadKeyEvent(date = Instant.parse("2000-12-31T00:00:00Z"), gamepadKey = GamepadKey.UNMAPPED),
            GamepadKeyEvent(date = Instant.parse("2000-12-31T00:00:01Z"), gamepadKey = GamepadKey.UNMAPPED),
            GamepadKeyEvent(date = Instant.parse("2000-12-31T00:00:02Z"), gamepadKey = GamepadKey.UNMAPPED),
        )

        // WHEN their duration (first to last) is calculated
        val sessionLength = events.calcDuration()

        // THEN it is calculated correctly
        assertEquals(2, sessionLength)
    }
}
