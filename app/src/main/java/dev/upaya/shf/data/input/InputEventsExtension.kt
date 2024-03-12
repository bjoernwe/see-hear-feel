package dev.upaya.shf.data.input

import dev.upaya.shf.ui.Label
import dev.upaya.shf.ui.LabelMapSHF
import kotlin.math.roundToInt


typealias LabelFreqs = Map<Label, Int>


internal fun List<InputEvent>.calcSessionLength(): Int {

    if (this.size < 2)
        return 0

    val firstDate = this.first()
    val lastDate = this.last()

    return (lastDate.date.time - firstDate.date.time).toFloat().div(1000f).roundToInt()
}


internal fun List<InputEvent>.toLabelFreqs(): Map<Label, Int> {
    return this.groupBy { inputEvent ->
        LabelMapSHF.getLabel(inputEvent.inputKey)
    } .mapValues { (_, dates) ->
        dates.size
    }
}
