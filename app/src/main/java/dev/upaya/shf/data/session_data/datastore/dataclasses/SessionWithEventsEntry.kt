package dev.upaya.shf.data.session_data.datastore.dataclasses

import androidx.room.Embedded
import androidx.room.Relation
import dev.upaya.shf.data.session_data.dataclasses.SessionWithEvents


data class SessionWithEventsEntry (

    @Embedded
    val session: SessionEntry,

    @Relation(parentColumn = "id", entityColumn = "sessionId")
    val notings: List<NotingEntry>,

    @Relation(parentColumn = "id", entityColumn = "sessionId")
    val delays: List<InputDelayEntry>,
) {
    fun toSessionWithEvents(): SessionWithEvents {
        return SessionWithEvents(
            session = session.toSession(),
            notings = notings.map { it.toNotingEvent() },
            delays = delays.map { it.toInputDelayEvent() },
        )
    }
}
