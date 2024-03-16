package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.gamepad.toLabelFreqs


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
