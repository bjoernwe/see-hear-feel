package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStatsDataSource @Inject constructor() {

    private val stats = InputEventStats(ioDispatcher = Dispatchers.IO)

    fun startStatsCollection(
        inputEventFlow: Flow<InputEvent>,
        coroutineScope: CoroutineScope,
    ) {
        stats.startStatsCollection(inputEventFlow = inputEventFlow, coroutineScope = coroutineScope)
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
