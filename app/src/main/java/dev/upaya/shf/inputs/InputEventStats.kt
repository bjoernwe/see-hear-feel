package dev.upaya.shf.inputs

import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labels.Label
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


typealias LabelFreqs = Map<Label, Int>


class InputEventStats(
    private val labelMap: LabelMap,
    private val inputEventFlow: Flow<InputEvent>,
    private val coroutineScope: CoroutineScope,
) {

    private val _inputEvents = mutableListOf<InputEvent>()
    internal val inputEvents: List<InputEvent> = _inputEvents

    private var inputEventCollectionJob: Job? = null

    val sessionTime: Int?
        get() = inputEvents.calcSessionLength()

    val labelFreqs: LabelFreqs
        get() = inputEvents.toLabelFreqs(labelMap)

    internal fun start() {
        inputEventCollectionJob = initStatsCollectionJob()
    }

    internal fun stop() {
        inputEventCollectionJob?.cancel()
    }

    private fun initStatsCollectionJob(): Job {
        return coroutineScope.launch {
            inputEventFlow.collect { inputEvent ->
                _inputEvents.add(inputEvent)
            }
        }
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
