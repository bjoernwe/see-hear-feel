package dev.upaya.shf.data.session_history

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.labels.SHFLabelDataSource
import kotlinx.coroutines.CoroutineScope
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

    fun startRecordingEvents(scope: CoroutineScope) {

        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                val notingEvent = NotingEvent(label = labelEvent.label, date = labelEvent.timestamp)
                notingEventDao.insertOrReplace(notingEvent)
            }
        }
    }
}
