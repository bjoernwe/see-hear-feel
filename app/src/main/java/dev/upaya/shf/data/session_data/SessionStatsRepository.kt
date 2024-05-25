package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.datastore.SessionDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    sessionDataStore: SessionDataStore,
) {
    val labelFreqs: Flow<Map<SHFLabel, Int>> = sessionDataStore.labelFreqs

    val numEvents: Flow<Int> = sessionDataStore.numEventsOfCurrentSession
    val sessionDurationSeconds: Flow<Long?> = sessionDataStore.latestSessionWithEvents.map(::calcSessionDuration)
    val amountMindWandering: Flow<Float> = sessionDataStore.latestSessionWithEvents.map(::calcMindWandering)

    val numEventsInDB: Flow<Int> = sessionDataStore.numEventsInDB
    val numOfSessions: Flow<Int> = sessionDataStore.numOfSesions
    val numOfDays: Flow<Int> = sessionDataStore.numOfDays

    val accumulatedNotingsPerDay: Flow<List<Pair<LocalDate, Int>>> = sessionDataStore.numOfNotingsPerDay.map(::calcAccumulatedNotingsPerDay)
}
