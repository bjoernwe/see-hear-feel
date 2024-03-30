package dev.upaya.shf.data.session_data.datastore.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.upaya.shf.data.session_data.datastore.dataclasses.InputDelayEntry


@Dao
interface InputDelayEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(inputDelayEntry: InputDelayEntry)

}
