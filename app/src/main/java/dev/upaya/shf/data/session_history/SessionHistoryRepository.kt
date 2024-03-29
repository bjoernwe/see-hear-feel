package dev.upaya.shf.data.session_history

import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.session_history.datastore.SessionHistoryDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionHistoryRepository @Inject constructor(
    private val shfLabelDataSource: SHFLabelDataSource,
    private val sessionHistoryDataStore: SessionHistoryDataStore,
    private val inputDelayEventDataSource: InputDelayEventDataSource,
) {

    fun startRecordingSessionEvents(scope: CoroutineScope) {
        scope.launch {
            sessionHistoryDataStore.createSessionResource().use { resource ->
                listOf(
                    recordNotingEvents(scope = scope, sessionId = resource.sessionId),
                    recordInputDelayEvents(scope = scope, sessionId = resource.sessionId),
                ).awaitAll()
            }
        }
    }

    private fun recordNotingEvents(scope: CoroutineScope, sessionId: Long): Deferred<Unit> {
        return scope.async {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                sessionHistoryDataStore.storeOrReplaceNotingEvent(labelEvent = labelEvent, sessionId = sessionId)
            }
        }
    }

    private fun recordInputDelayEvents(scope: CoroutineScope, sessionId: Long): Deferred<Unit> {
        return scope.async {
            inputDelayEventDataSource.getInputDelayEvents(scope = scope).collect { inputDelayEvent ->
                sessionHistoryDataStore.storeOrReplaceInputDelayEvent(inputDelayEvent = inputDelayEvent, sessionId = sessionId)
            }
        }
    }
}
