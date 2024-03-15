package dev.upaya.shf.data.stats

import dev.upaya.shf.data.input.GamepadKeyEvent
import dev.upaya.shf.data.input.LabelFreqs
import dev.upaya.shf.data.input.toLabelFreqs


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
