package dev.upaya.shf.data.session_history.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant


const val INPUT_DELAY_EVENT_TABLE_NAME = "input_delay_events"


@Entity(tableName = INPUT_DELAY_EVENT_TABLE_NAME)
data class InputDelayEvent(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "count")
    val delaysInARow: Int,

    @ColumnInfo(name = "interval")
    val delayInterval: Int,

    val timestamp: Instant = Instant.now(),
)
