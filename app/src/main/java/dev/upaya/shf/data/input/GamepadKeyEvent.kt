package dev.upaya.shf.data.input

import java.time.Instant


data class GamepadKeyEvent(
    val gamepadKey: GamepadKey,
    val date: Instant = Instant.now(),
) {
    companion object {
        val ZERO = GamepadKeyEvent(
            gamepadKey = GamepadKey.UNMAPPED,
            date = Instant.ofEpochSecond(0)
        )
    }
}
