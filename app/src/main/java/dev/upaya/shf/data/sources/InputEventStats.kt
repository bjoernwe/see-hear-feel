package dev.upaya.shf.data.sources

import dev.upaya.shf.ui.LabelMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


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