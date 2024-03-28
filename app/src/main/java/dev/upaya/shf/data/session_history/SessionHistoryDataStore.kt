package dev.upaya.shf.data.session_history

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_history.dataclasses.NotingEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    private val notingEventDao = db.getNotingEventDao()
    private val inputDelayDao = db.getInputDelayDao()

    val numEventsInDB: Flow<Int> = notingEventDao.countEvents()
    val numOfDays: Flow<Int> = notingEventDao.countEventsPerDay().map { it.size }

    suspend fun storeOrReplaceNotingEvent(labelEvent: SHFLabelEvent) {
        notingEventDao.insertOrReplace(NotingEntry.from(labelEvent))
    }

    suspend fun storeOrReplaceInputDelayEvent(inputDelayEvent: InputDelayEvent) {
        inputDelayDao.insertOrReplace(InputDelayEntry.from(inputDelayEvent))
    }
}
