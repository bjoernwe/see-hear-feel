package dev.upaya.shf.data.labels


internal fun List<SHFLabelEvent>.calcDuration(): Int {

    if (this.size < 2)
        return 0

    val firstDate = this.first().timestamp.toEpochSecond()
    val lastDate = this.last().timestamp.toEpochSecond()

    return (lastDate - firstDate).toInt()
}
