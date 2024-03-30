package dev.upaya.shf.data.session_data

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.datastore.SessionDataStore
import dev.upaya.shf.data.session_data.datastore.SessionResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionDataRepository @Inject constructor(
    private val shfLabelDataSource: SHFLabelDataSource,
    private val sessionDataStore: SessionDataStore,
    private val inputDelayEventDataSource: InputDelayEventDataSource,
) {

    suspend fun createSessionResource(): SessionResource {
        return sessionDataStore.createSessionResource()
    }

    fun addLabelEventListener(scope: CoroutineScope, onLabelEvent: (SHFLabelEvent) -> Unit) {
        scope.launch {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                onLabelEvent(labelEvent)
            }
        }
    }

    fun addInputDelayListener(scope: CoroutineScope, onInputDelay: (InputDelayEvent) -> Unit) {
        scope.launch {
            inputDelayEventDataSource.getInputDelayEvents(scope = scope).collect { inputDelayEvent ->
                onInputDelay(inputDelayEvent)
            }
        }
    }

    suspend fun storeNotingEvent(labelEvent: SHFLabelEvent, sessionId: Long) {
        sessionDataStore.storeOrReplaceNotingEvent(labelEvent = labelEvent, sessionId = sessionId)
    }

    suspend fun storeInputDelayEvent(inputDelayEvent: InputDelayEvent, sessionId: Long) {
        sessionDataStore.storeOrReplaceInputDelayEvent(inputDelayEvent = inputDelayEvent, sessionId = sessionId)
    }
}
