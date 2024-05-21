package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingsPerDay
import java.time.LocalDate


internal fun calcSessionDuration(session: SessionWithEvents): Long {
    val notingsStart = session.notings.first().timestamp.epochSecond
    val notingsEnd = session.notings.last().timestamp.epochSecond
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
