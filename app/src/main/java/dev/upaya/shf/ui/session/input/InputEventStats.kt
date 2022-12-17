package dev.upaya.shf.ui.session.input

import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.InputEvent
import kotlin.math.roundToInt


typealias LabelFreqs = Map<Label, Int>


class InputEventStats(
    private val labelMap: LabelMap,
) {

    private val _inputEvents = mutableListOf<InputEvent>()
    internal val inputEvents: List<InputEvent> = _inputEvents

    val sessionTime: Int?
        get() = inputEvents.calcSessionLength()

    val labelFreqs: LabelFreqs
        get() = inputEvents.toLabelFreqs(labelMap)

    fun reportInputEvent(inputEvent: InputEvent) {
        _inputEvents.add(inputEvent)
    }

}


private fun List<InputEvent>.calcSessionLength(): Int? {

    if (this.size < 2)
        return null

    val firstDate = this.first()
    val lastDate = this.last()

    return (lastDate.date.time - firstDate.date.time).toFloat().div(1000f).roundToInt()
}


private fun List<InputEvent>.toLabelFreqs(labelMap: LabelMap): Map<Label, Int> {
    return this.groupBy { inputEvent ->
        labelMap.getLabel(inputEvent.inputKey)
    } .mapValues { (_, dates) ->
        dates.size
    }
}
