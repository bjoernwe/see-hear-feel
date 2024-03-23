package dev.upaya.shf.data.session_history

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.session_stats.AllTimeStats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


private const val DB_NAME = "dev.upaya.shf.session_db"


@Singleton
class NotingEventHistoryRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val shfLabelDataSource: SHFLabelDataSource,
) {

    private val db = Room.databaseBuilder(
        appContext,
        NotingEventDatabase::class.java,
        DB_NAME
    ).build()

    private val notingEventDao = db.getNotingEventDao()

    private val numEventsInDB: Flow<Int> = notingEventDao.countEvents()
    private val numOfDays: Flow<Int> = notingEventDao.countEventsPerDay().map { it.size }

    val allTimeStats: Flow<AllTimeStats> = combine(numEventsInDB, numOfDays) { numEvents, numDays ->
        AllTimeStats(numNotings = numEvents, numDays = numDays,)
    }

    fun startRecordingEvents(scope: CoroutineScope) {
        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                notingEventDao.insertOrReplace(
                    NotingEvent(label = labelEvent.label, date = labelEvent.timestamp)
                )
            }
        }
    }
}
