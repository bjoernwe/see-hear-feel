package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.gamepad_input.GamepadKeyEvent
import dev.upaya.shf.data.gamepad_input.LabelFreqs
import dev.upaya.shf.data.gamepad_input.toLabelFreqs


data class SessionStats (
    val labelFreqs: LabelFreqs,
) {

    companion object {
        fun fromInputEvents(gamepadKeyEvents: List<GamepadKeyEvent>): SessionStats {
            return SessionStats(
                labelFreqs = gamepadKeyEvents.toLabelFreqs(),
            )
        }
    }

}
