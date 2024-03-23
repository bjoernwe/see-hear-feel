package dev.upaya.shf.data.session_history

import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.session_history.dataclasses.NotingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionHistoryRepository @Inject constructor(
    private val shfLabelDataSource: SHFLabelDataSource,
    private val sessionHistoryDataStore: SessionHistoryDataStore,
    private val inputDelayEventDataSource: InputDelayEventDataSource,
) {

    fun startRecordingEvents(scope: CoroutineScope) {
        val notingEventDao = sessionHistoryDataStore.getNotingEventDao()
        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                notingEventDao.insertOrReplace(
                    NotingEvent(label = labelEvent.label, date = labelEvent.timestamp)
                )
            }
        }
        scope.launch {
            val inputDelayEventDao = sessionHistoryDataStore.getInputDelayEventDao()
            scope.launch {
                inputDelayEventDataSource.getInputDelayEvents(scope = scope).collect {
                    inputDelayEventDao.insertOrReplace(it)
                }
            }
        }
    }

}
