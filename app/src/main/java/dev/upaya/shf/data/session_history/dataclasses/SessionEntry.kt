package dev.upaya.shf.data.session_history.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime


const val SESSION_TABLE_NAME = "sessions"


@Entity(tableName = SESSION_TABLE_NAME)
data class SessionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val start: OffsetDateTime,
    var end: OffsetDateTime? = null,
) {
    companion object {
        fun from(session: Session): SessionEntry {
            return SessionEntry(
                id = session.id,
                start = session.start,
                end = session.end,
            )
        }
    }
}
