package dev.upaya.shf.data.session_history

import dev.upaya.shf.data.labels.SHFLabelDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionHistoryRepository @Inject constructor(
    private val shfLabelDataSource: SHFLabelDataSource,
    private val sessionHistoryDataStore: SessionHistoryDataStore,
) {

    fun startRecordingEvents(scope: CoroutineScope) {
        val notingEventDao = sessionHistoryDataStore.getNotinEventDao()
        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                notingEventDao.insertOrReplace(
                    NotingEvent(label = labelEvent.label, date = labelEvent.timestamp)
                )
            }
        }
    }

}
