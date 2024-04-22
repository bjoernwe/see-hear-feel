package dev.upaya.shf.data.labels


internal fun List<SHFLabelEvent>.calcDuration(): Int {

    if (this.size < 2)
        return 0

    val firstDate = this.first().timestamp.epochSecond
    val lastDate = this.last().timestamp.epochSecond

    return (lastDate - firstDate).toInt()
}
