package dev.upaya.shf.data.session_data.datastore.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.upaya.shf.data.session_data.datastore.dataclasses.SESSION_TABLE_NAME
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionWithEventsEntry
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime


@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(session: SessionEntry): Long

    @Query("DELETE FROM $SESSION_TABLE_NAME WHERE id = :id")
    suspend fun deleteSession(id: Long)

    @Query("SELECT COUNT(*) FROM $SESSION_TABLE_NAME")
    fun countSessions(): Flow<Int>

    @Query("SELECT * FROM $SESSION_TABLE_NAME ORDER BY id DESC LIMIT 1")
    fun getNewestSession(): Flow<SessionEntry>

    @Query("SELECT * FROM $SESSION_TABLE_NAME WHERE id = :id")
    suspend fun getSession(id: Long): SessionEntry

    @Transaction
    @Query("SELECT * FROM $SESSION_TABLE_NAME WHERE id = (SELECT id FROM $SESSION_TABLE_NAME ORDER BY id DESC LIMIT 1)")
    fun getLatestSessionWithEvents(): Flow<SessionWithEventsEntry>

    @Query("UPDATE $SESSION_TABLE_NAME SET 'end' = :end WHERE id = :id")
    suspend fun setEndTimestamp(id: Long, end: OffsetDateTime = OffsetDateTime.now())
}
