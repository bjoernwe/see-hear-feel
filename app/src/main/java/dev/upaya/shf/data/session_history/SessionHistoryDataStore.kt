package dev.upaya.shf.data.session_history

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.session_history.daos.InputDelayEventDao
import dev.upaya.shf.data.session_history.daos.NotingEventDao
import javax.inject.Inject
import javax.inject.Singleton


private const val DB_NAME = "dev.upaya.shf.session_db"


@Singleton
class SessionHistoryDataStore @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {

    private val db = Room.databaseBuilder(
        appContext,
        NotingEventDatabase::class.java,
        DB_NAME
    ).build()

    fun getNotingEventDao(): NotingEventDao {
        return db.getNotingEventDao()
    }

    fun getInputDelayEventDao(): InputDelayEventDao {
        return db.getInputDelayDao()
    }
}
