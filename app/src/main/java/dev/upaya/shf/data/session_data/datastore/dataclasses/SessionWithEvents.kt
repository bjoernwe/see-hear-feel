package dev.upaya.shf.data.session_data.datastore.dataclasses

import androidx.room.Embedded
import androidx.room.Relation


data class SessionWithEvents (

    @Embedded
    val session: SessionEntry,

    @Relation(parentColumn = "id", entityColumn = "sessionId")
    val notings: List<NotingEntry>,

    @Relation(parentColumn = "id", entityColumn = "sessionId")
    val delays: List<InputDelayEntry>,
)
