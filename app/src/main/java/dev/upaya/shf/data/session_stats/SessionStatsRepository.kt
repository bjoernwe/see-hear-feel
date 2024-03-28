package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.session_history.SessionHistoryDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val shfLabelDataSource: SHFLabelDataSource,
    private val sessionStatsDataSource: SessionStatsDataSource,
    sessionHistoryDataStore: SessionHistoryDataStore,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    val numEvents: StateFlow<Int> = sessionStatsDataSource.numEvents
    val sessionDurationSeconds: StateFlow<Int> = sessionStatsDataSource.sessionDurationSeconds

    private val numEventsInDB: Flow<Int> = sessionHistoryDataStore.numEventsInDB
    private val numOfDays: Flow<Int> = sessionHistoryDataStore.numOfDays

    val allTimeStats: Flow<AllTimeStats> = combine(numEventsInDB, numOfDays) { numEvents, numDays ->
        AllTimeStats(numNotings = numEvents, numDays = numDays)
    }

    suspend fun calcStats(): SessionStats {
        return sessionStatsDataSource.calcStats()
    }

    fun startStatsCollection(scope: CoroutineScope) {

        sessionStatsDataSource.reset()

        scope.launch(defaultDispatcher) {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                sessionStatsDataSource.addInputEvent(labelEvent)
            }
        }
    }

}
