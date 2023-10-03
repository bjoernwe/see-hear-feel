package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val sessionStatsDataSource: SessionStatsDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    val numEvents: StateFlow<Int> = sessionStatsDataSource.numEvents
    val sessionLength: StateFlow<Int> = sessionStatsDataSource.sessionLength

    suspend fun calcStats(): SessionStats {
        return sessionStatsDataSource.calcStats()
    }

    suspend fun startStatsCollection(inputEventFlow: Flow<InputEvent>) {
        withContext(defaultDispatcher) {
            sessionStatsDataSource.reset()
            inputEventFlow.collect { inputEvent ->
                sessionStatsDataSource.addInputEvent(inputEvent)
            }
        }
    }

}
