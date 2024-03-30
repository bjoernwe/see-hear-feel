package dev.upaya.shf.data.session_history.datastore.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.upaya.shf.data.session_history.Session
import java.time.OffsetDateTime


const val SESSION_TABLE_NAME = "sessions"


@Entity(tableName = SESSION_TABLE_NAME)
data class SessionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val start: OffsetDateTime,
    var end: OffsetDateTime? = null,
) {
    fun toSession(): Session {
        return Session(id = id.toLong(), start = start, end = end)
    }
}
