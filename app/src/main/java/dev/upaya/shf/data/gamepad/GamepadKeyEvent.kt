package dev.upaya.shf.data.gamepad

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime


data class GamepadKeyEvent(
    val gamepadKey: GamepadKey,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
) {
    companion object {
        val ZERO = GamepadKeyEvent(
            gamepadKey = GamepadKey.UNMAPPED,
            timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.systemDefault())
        )
    }
}
