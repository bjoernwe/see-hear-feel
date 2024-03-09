package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val sessionStatsDataSource: SessionStatsDataSource,
    private val keyPressDataSource: KeyPressDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    val numEvents: StateFlow<Int> = sessionStatsDataSource.numEvents
    val sessionLength: StateFlow<Int> = sessionStatsDataSource.sessionLength

    suspend fun calcStats(): SessionStats {
        return sessionStatsDataSource.calcStats()
    }

    suspend fun startStatsCollection() {

        // Drop current state of StateFlow
        val eventFlow = keyPressDataSource.inputKeyDown.drop(1)

        sessionStatsDataSource.reset()

        withContext(defaultDispatcher) {
            eventFlow.collect { inputEvent ->
                sessionStatsDataSource.addInputEvent(inputEvent)
            }
        }
    }

}
