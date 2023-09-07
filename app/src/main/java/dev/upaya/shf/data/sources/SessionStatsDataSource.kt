package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStatsDataSource @Inject constructor() {

    private val stats = InputEventCollection(ioDispatcher = Dispatchers.IO)

    private val _numEvents = MutableStateFlow(0)
    val numEvents: StateFlow<Int> = _numEvents

    fun startStatsCollection(
        inputEventFlow: Flow<InputEvent>,
        coroutineScope: CoroutineScope,
    ) {
        stats.startStatsCollection(
            inputEventFlow = inputEventFlow,
            coroutineScope = coroutineScope,
            onEventCollected = { allEvents ->
                _numEvents.value = allEvents.size
            }
        )
    }

    fun stopStatsCollection() {
        stats.stopStatsCollection()
    }

    fun getNumEvents(): Int {
        return stats.inputEvents.size
    }

    fun getSessionLength(): Int? {
        return stats.sessionTime
    }

    fun getLabelFreqs(): LabelFreqs {
        return stats.labelFreqs
    }
}
