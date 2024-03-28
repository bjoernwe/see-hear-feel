package dev.upaya.shf.data.session_history.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.upaya.shf.data.session_history.dataclasses.NOTING_EVENT_TABLE_NAME
import dev.upaya.shf.data.session_history.dataclasses.NotingEntry
import dev.upaya.shf.data.session_history.dataclasses.NotingsPerDay
import kotlinx.coroutines.flow.Flow


@Dao
interface NotingEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(notingEntry: NotingEntry)

    @Query("SELECT * FROM $NOTING_EVENT_TABLE_NAME")
    suspend fun loadAllNotingEvents(): Array<NotingEntry>

    @Query("SELECT COUNT(*) FROM $NOTING_EVENT_TABLE_NAME")
    fun countEvents(): Flow<Int>

    @Query("SELECT COUNT(date) as count, date(date) as day FROM $NOTING_EVENT_TABLE_NAME GROUP BY day ORDER BY day")
    fun countEventsPerDay(): Flow<List<NotingsPerDay>>
}
