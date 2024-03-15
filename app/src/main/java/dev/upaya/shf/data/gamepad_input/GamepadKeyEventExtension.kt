package dev.upaya.shf.data.gamepad_input


internal fun List<SHFLabelEvent>.calcDuration(): Int {

    if (this.size < 2)
        return 0

    val firstDate = this.first()
    val lastDate = this.last()

    return (lastDate.timestamp.epochSecond - firstDate.timestamp.epochSecond).toInt()
}


internal fun List<SHFLabelEvent>.toLabelFreqs(): Map<SHFLabel, Int> {
    return this.groupBy { it.label } .mapValues { (_, labels) ->
        labels.size
    }
}
