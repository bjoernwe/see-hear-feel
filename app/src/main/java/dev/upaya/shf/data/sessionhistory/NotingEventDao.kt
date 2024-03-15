package dev.upaya.shf.data.sessionhistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NotingEventDao {

    @Insert
    suspend fun insert(notingEvent: NotingEvent)

    @Query("SELECT * FROM $NOTING_EVENT_TABLE_NAME")
    suspend fun loadAllNotingEvents(): Array<NotingEvent>

}
