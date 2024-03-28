package dev.upaya.shf.data.session_history

import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.data.labels.SHFLabelDataSource
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
        startRecordingNotingEvents(scope)
        startRecordingInputDelayEvents(scope)
    }

    private fun startRecordingNotingEvents(scope: CoroutineScope) {
        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                sessionHistoryDataStore.storeOrReplaceNotingEvent(labelEvent)
            }
        }
    }

    private fun startRecordingInputDelayEvents(scope: CoroutineScope) {
        scope.launch {
            inputDelayEventDataSource.getInputDelayEvents(scope = scope).collect {
                sessionHistoryDataStore.storeOrReplaceInputDelayEvent(it)
            }
        }
    }
}
