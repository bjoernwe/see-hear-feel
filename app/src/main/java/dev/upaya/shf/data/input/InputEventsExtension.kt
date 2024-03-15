package dev.upaya.shf.data.input

import dev.upaya.shf.ui.Label
import dev.upaya.shf.ui.LabelMapSHF


typealias LabelFreqs = Map<Label, Int>


internal fun List<GamepadKeyEvent>.calcSessionLength(): Int {

    if (this.size < 2)
        return 0

    val firstDate = this.first()
    val lastDate = this.last()

    return (lastDate.date.epochSecond - firstDate.date.epochSecond).toInt()
}


internal fun List<GamepadKeyEvent>.toLabelFreqs(): Map<Label, Int> {
    return this.groupBy { inputEvent ->
        LabelMapSHF.getLabel(inputEvent.gamepadKey)
    } .mapValues { (_, dates) ->
        dates.size
    }
}
