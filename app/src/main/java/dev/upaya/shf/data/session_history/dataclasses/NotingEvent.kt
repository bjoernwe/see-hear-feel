package dev.upaya.shf.data.session_history.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.upaya.shf.data.labels.SHFLabel
import java.time.Instant


const val NOTING_EVENT_TABLE_NAME = "noting_events"


@Entity(tableName = NOTING_EVENT_TABLE_NAME)
data class NotingEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Instant? = Instant.now(),
    val label: SHFLabel,
)
