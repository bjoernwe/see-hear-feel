package dev.upaya.shf.data.gamepad

import java.time.Instant


data class GamepadKeyEvent(
    val gamepadKey: GamepadKey,
    val timestamp: Instant = Instant.now(),
) {
    companion object {
        val ZERO = GamepadKeyEvent(
            gamepadKey = GamepadKey.UNMAPPED,
            timestamp = Instant.ofEpochSecond(0)
        )
    }
}
