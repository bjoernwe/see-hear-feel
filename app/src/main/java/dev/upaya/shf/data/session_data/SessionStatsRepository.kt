package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.dataclasses.AllTimeStats
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import dev.upaya.shf.data.session_data.datastore.SessionDataStore
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingsPerDay
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


internal fun calcSessionDuration(session: SessionWithEvents): Long {
    val notingsStart = session.notings.first().timestamp.toEpochSecond()
    val notingsEnd = session.notings.last().timestamp.toEpochSecond()
    return notingsEnd - notingsStart
}


internal fun calcAccumulatedNotingsPerDay(notingsPerDays: List<NotingsPerDay>): List<Pair<LocalDate, Int>> {
    val result = mutableListOf<Pair<LocalDate, Int>>()
    notingsPerDays.forEach { notingsPerDay ->
        val lastAccumulation: Int = result.lastOrNull()?.second ?: 0
        val newAccumulation: Int = lastAccumulation + notingsPerDay.count
        result.add(Pair(notingsPerDay.day, newAccumulation))
    }
    return result
}
