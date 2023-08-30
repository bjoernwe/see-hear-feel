package dev.upaya.shf.data.sources

import dev.upaya.shf.ui.LabelMapSHF
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class InputEventStats(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val _inputEvents = mutableListOf<InputEvent>()
    internal val inputEvents: List<InputEvent> = _inputEvents

    private var inputEventCollectionJob: Job? = null

    private val labelMap = LabelMapSHF

    val sessionTime: Int?
        get() = inputEvents.calcSessionLength()

    val labelFreqs: LabelFreqs
        get() = inputEvents.toLabelFreqs(labelMap)  // TODO: Should be calculated off the main thread

    internal fun startStatsCollection(
        inputEventFlow: Flow<InputEvent>,
        coroutineScope: CoroutineScope,
    ) {
        inputEventCollectionJob = coroutineScope
            .launch(ioDispatcher) {
                inputEventFlow.collect { inputEvent ->
                    _inputEvents.add(inputEvent)
                }
            }
    }

    internal fun stopStatsCollection() {
        inputEventCollectionJob?.cancel()
    }
}