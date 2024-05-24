package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.dataclasses.AllTimeStats
import dev.upaya.shf.data.session_data.datastore.SessionDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    sessionDataStore: SessionDataStore,
) {
    val numEvents: Flow<Int> = sessionDataStore.numEventsOfCurrentSession
    val labelFreqs: Flow<Map<SHFLabel, Int>> = sessionDataStore.labelFreqs
    val sessionDurationSeconds: Flow<Long?> = sessionDataStore.latestSessionWithEvents.map(::calcSessionDuration)
    val accumulatedNotingsPerDay: Flow<List<Pair<LocalDate, Int>>> = sessionDataStore.numOfNotingsPerDay.map(::calcAccumulatedNotingsPerDay)
    val amountMindWandering: Flow<Float> = sessionDataStore.latestSessionWithEvents.map(::calcMindWandering)

    private val numEventsInDB: Flow<Int> = sessionDataStore.numEventsInDB
    private val numOfSessions: Flow<Int> = sessionDataStore.numOfSesions
    private val numOfDays: Flow<Int> = sessionDataStore.numOfDays

    val allTimeStats: Flow<AllTimeStats> = combine(
        numEventsInDB,
        numOfSessions,
        numOfDays,
    ) { numEvents, numSessions, numDays ->
        AllTimeStats(
            numNotings = numEvents,
            numSessions = numSessions,
            numDays = numDays,
        )
    }

}
