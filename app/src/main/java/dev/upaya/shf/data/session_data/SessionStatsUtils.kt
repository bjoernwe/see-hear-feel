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


internal fun calcMindWandering(session: SessionWithEvents): Float {
    val delaysDuringActiveNoting = session.delays.filter { it.timestamp.isBefore(session.notings.last().timestamp) }
    val secondsMindWandering = delaysDuringActiveNoting.fold(0L) { acc, delay -> acc + delay.delayInterval }
    val secondsActivelyNoting = session.notings.last().timestamp.epochSecond - session.notings.first().timestamp.epochSecond
    val activeWithoutMindWandering = (secondsActivelyNoting - secondsMindWandering).toFloat().div(secondsActivelyNoting)
    return 1f - activeWithoutMindWandering
}
