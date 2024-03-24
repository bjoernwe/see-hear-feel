package dev.upaya.shf.data.session_history.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEvent


@Dao
interface InputDelayEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(inputDelayEvent: InputDelayEvent)

}
