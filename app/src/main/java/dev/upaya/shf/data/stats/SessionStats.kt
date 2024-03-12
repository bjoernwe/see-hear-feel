package dev.upaya.shf.data.stats

import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.LabelFreqs
import dev.upaya.shf.data.sources.toLabelFreqs


data class SessionStats (
    val labelFreqs: LabelFreqs,
) {

    companion object {
        fun fromInputEvents(inputEvents: List<InputEvent>): SessionStats {
            return SessionStats(
                labelFreqs = inputEvents.toLabelFreqs(),
            )
        }
    }

}
