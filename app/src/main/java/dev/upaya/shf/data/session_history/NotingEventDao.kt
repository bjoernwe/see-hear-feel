package dev.upaya.shf.data.session_history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NotingEventDao {

    @Insert
    suspend fun insert(notingEvent: NotingEvent)

    @Query("SELECT * FROM $NOTING_EVENT_TABLE_NAME")
    suspend fun loadAllNotingEvents(): Array<NotingEvent>

    @Query("SELECT COUNT(*) FROM $NOTING_EVENT_TABLE_NAME")
    fun countEvents(): Flow<Int>;

}
