package dev.upaya.shf.data.session_data.datastore.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.upaya.shf.data.session_data.datastore.dataclasses.NOTING_EVENT_TABLE_NAME
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingsPerDay
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

    /*
     * Design decision: Here is a good place for cross-table queries that include session as well as
     * events. Because events know about session, but a session doesn't need to know all the things
     * that are linking to it.
     */

    @Query("SELECT COUNT(*) FROM $NOTING_EVENT_TABLE_NAME WHERE sessionId = $LATEST_SESSION_ID")
    fun countEventsOfCurrentSession(): Flow<Int>

    @Query("SELECT label, COUNT(label) AS count FROM $NOTING_EVENT_TABLE_NAME WHERE sessionId = $LATEST_SESSION_ID GROUP BY label")
    fun getEventsPerLabelForCurrentSession(): Flow<Map<@MapColumn("label") String, @MapColumn("count") Int>>
}
