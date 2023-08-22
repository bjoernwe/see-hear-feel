package dev.upaya.shf.data.sources

import dev.upaya.shf.ui.LabelMap
import dev.upaya.shf.ui.Label
import kotlin.math.roundToInt


typealias LabelFreqs = Map<Label, Int>


internal fun List<InputEvent>.calcSessionLength(): Int? {

    if (this.size < 2)
        return null

    val firstDate = this.first()
    val lastDate = this.last()

    return (lastDate.date.time - firstDate.date.time).toFloat().div(1000f).roundToInt()
}


internal fun List<InputEvent>.toLabelFreqs(labelMap: LabelMap): Map<Label, Int> {
    return this.groupBy { inputEvent ->
        labelMap.getLabel(inputEvent.inputKey)
    } .mapValues { (_, dates) ->
        dates.size
    }
}
