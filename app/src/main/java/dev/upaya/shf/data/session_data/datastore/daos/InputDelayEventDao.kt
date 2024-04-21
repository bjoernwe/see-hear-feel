package dev.upaya.shf.data.session_data.datastore.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.upaya.shf.data.session_data.datastore.dataclasses.INPUT_DELAY_EVENT_TABLE_NAME
import dev.upaya.shf.data.session_data.datastore.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SESSION_TABLE_NAME
import kotlinx.coroutines.flow.Flow


@Dao
interface InputDelayEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(inputDelayEntry: InputDelayEntry)

    @Query("SELECT * FROM $INPUT_DELAY_EVENT_TABLE_NAME WHERE sessionId = (SELECT id FROM $SESSION_TABLE_NAME ORDER BY id DESC LIMIT 1)")
    fun getDelaysOfCurrentSession(): Flow<List<InputDelayEntry>>
}
