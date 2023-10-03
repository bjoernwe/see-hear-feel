package dev.upaya.shf.data.sources


data class SessionStats constructor(
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
