package dev.upaya.shf.data.session_history.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.upaya.shf.data.delay.InputDelayEvent
import java.time.OffsetDateTime


const val INPUT_DELAY_EVENT_TABLE_NAME = "input_delay_events"


@Entity(tableName = INPUT_DELAY_EVENT_TABLE_NAME)
data class InputDelayEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "count")
    val delaysInARow: Int,

    @ColumnInfo(name = "interval")
    val delayInterval: Int,

    val timestamp: OffsetDateTime = OffsetDateTime.now(),
) {
    companion object {
        fun from(inputDelayEvent: InputDelayEvent): InputDelayEntry {
            return InputDelayEntry(
                delaysInARow = inputDelayEvent.delaysInARow,
                delayInterval = inputDelayEvent.delayInterval,
                timestamp = inputDelayEvent.timestamp,
            )
        }
    }
}
