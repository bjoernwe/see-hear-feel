package dev.upaya.shf.data.session_history.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.upaya.shf.data.session_history.dataclasses.SESSION_TABLE_NAME
import dev.upaya.shf.data.session_history.dataclasses.SessionEntry
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime


@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(session: SessionEntry): Long

    @Query("SELECT COUNT(*) FROM $SESSION_TABLE_NAME")
    fun countSessions(): Flow<Int>

    @Query("SELECT * FROM $SESSION_TABLE_NAME WHERE id = :id")
    suspend fun getSession(id: Long): SessionEntry

    @Query("UPDATE $SESSION_TABLE_NAME SET 'end' = :end WHERE id = :id")
    suspend fun setEndTimestamp(id: Long, end: OffsetDateTime = OffsetDateTime.now())
}
