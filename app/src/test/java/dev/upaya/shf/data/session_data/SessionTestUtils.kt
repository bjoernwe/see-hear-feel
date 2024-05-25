package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.dataclasses.Session
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


internal fun timestamp(
    year: Int = 2000,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): Instant {
    return LocalDateTime.of(year, month, day, hour, minute, second).toInstant(ZoneOffset.UTC)
}


fun createSession(sessionStart: Instant, init: SessionFactoryScope.() -> Unit): SessionWithEvents {
    val sessionFactoryScope = SessionFactoryScope()
    sessionFactoryScope.init()
    return SessionWithEvents(
        session = Session(start = sessionStart, end = sessionFactoryScope.sessionEnd),
        notings = sessionFactoryScope.notings,
        delays = sessionFactoryScope.delays,
    )
}


class SessionFactoryScope {
    var sessionEnd: Instant? = null
    val notings: MutableList<SHFLabelEvent> = mutableListOf()
    val delays: MutableList<InputDelayEvent> = mutableListOf()

    fun note(timestamp: Instant, label: SHFLabel = SHFLabel.GONE) {
        notings.add(SHFLabelEvent(timestamp = timestamp, label = label))
    }

    fun delay(timestamp: Instant) {
        val secondsSinceLastEvent = timestamp.epochSecond - lastEventTimestamp().epochSecond
        delays.add(
            InputDelayEvent(
                timestamp = timestamp,
                delayInterval = secondsSinceLastEvent.toInt(),
                delaysInARow = getNumOfRecentDelays() + 1,
            )
        )
    }

    fun endSession(timestamp: Instant) {
        sessionEnd = timestamp
    }

    private fun getNumOfRecentDelays(): Int {

        if (lastEventWasANoting())
            return 0

        return delays.lastOrNull()?.delaysInARow ?: 0
    }

    private fun lastEventWasANoting(): Boolean {
        val lastNoting = notings.lastOrNull()?.timestamp ?: Instant.ofEpochSecond(0)
        val lastDelay = delays.lastOrNull()?.timestamp ?: Instant.ofEpochSecond(0)
        return lastNoting > lastDelay
    }

    private fun lastEventTimestamp(): Instant {
        val lastNoting = notings.lastOrNull()?.timestamp ?: Instant.ofEpochSecond(0)
        val lastDelay = delays.lastOrNull()?.timestamp ?: Instant.ofEpochSecond(0)
        return maxOf(lastNoting, lastDelay)
    }
}
