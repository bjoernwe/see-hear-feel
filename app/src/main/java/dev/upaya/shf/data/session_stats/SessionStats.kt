package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.gamepad_input.SHFLabel
import dev.upaya.shf.data.gamepad_input.SHFLabelEvent
import dev.upaya.shf.data.gamepad_input.toLabelFreqs


data class SessionStats (
    val labelFreqs: Map<SHFLabel, Int>,
) {

    companion object {
        fun fromLabelEvents(labelEvents: List<SHFLabelEvent>): SessionStats {
            return SessionStats(
                labelFreqs = labelEvents.toLabelFreqs(),
            )
        }
    }

}
