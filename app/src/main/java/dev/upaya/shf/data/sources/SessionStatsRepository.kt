package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val sessionStatsDataSource: SessionStatsDataSource,
) {

    val numEvents = sessionStatsDataSource.numEvents

    fun startStatsCollection(
        inputEventFlow: Flow<InputEvent>,
        coroutineScope: CoroutineScope,
    ) {
        sessionStatsDataSource.startStatsCollection(
            inputEventFlow = inputEventFlow,
            coroutineScope = coroutineScope
        )
    }

    fun stopStatsCollection() {
        sessionStatsDataSource.stopStatsCollection()
    }

    fun getNumEvents(): Int {
        return sessionStatsDataSource.getNumEvents()
    }

    fun getSessionLength(): Int? {
        return sessionStatsDataSource.getSessionLength()
    }

    fun getLabelFreqs(): LabelFreqs {
        return sessionStatsDataSource.getLabelFreqs()
    }
}
