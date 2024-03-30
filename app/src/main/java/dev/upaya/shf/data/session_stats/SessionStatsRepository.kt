package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.session_history.datastore.SessionHistoryDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    sessionHistoryDataStore: SessionHistoryDataStore,
) {

    val numEvents: Flow<Int> = sessionHistoryDataStore.numEventsOfCurrentSession
    val sessionDurationSeconds: Flow<Long?> = sessionHistoryDataStore.newestSession.map { session ->
        session.end?.toEpochSecond()?.minus(session.start.toEpochSecond())
    }

    private val numEventsInDB: Flow<Int> = sessionHistoryDataStore.numEventsInDB
    private val numOfSessions: Flow<Int> = sessionHistoryDataStore.numOfSesions
    private val numOfDays: Flow<Int> = sessionHistoryDataStore.numOfDays

    val allTimeStats: Flow<AllTimeStats> = combine(
        numEventsInDB,
        numOfSessions,
        numOfDays
    ) { numEvents, numSessions, numDays ->
        AllTimeStats(
            numNotings = numEvents,
            numSessions = numSessions,
            numDays = numDays
        )
    }
}
