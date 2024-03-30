package dev.upaya.shf.data.session_history.datastore.dataclasses

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import java.time.OffsetDateTime


const val NOTING_EVENT_TABLE_NAME = "noting_events"


@Entity(
    tableName = NOTING_EVENT_TABLE_NAME,
    indices = [Index(value = ["sessionId"])],
)
data class NotingEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: OffsetDateTime? = OffsetDateTime.now(),
    val label: SHFLabel,
    val sessionId: Long? = null,
) {
    companion object {
        fun from(labelEvent: SHFLabelEvent, sessionId: Long): NotingEntry {
            return NotingEntry(
                label = labelEvent.label,
                date = labelEvent.timestamp,
                sessionId = sessionId,
            )
        }
    }
}
