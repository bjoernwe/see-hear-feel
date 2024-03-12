package dev.upaya.shf.data.stats

import dev.upaya.shf.data.input.InputEvent
import dev.upaya.shf.data.input.LabelFreqs
import dev.upaya.shf.data.input.toLabelFreqs


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
