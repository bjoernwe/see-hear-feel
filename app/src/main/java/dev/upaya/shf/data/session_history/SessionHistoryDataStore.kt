package dev.upaya.shf.data.session_history

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_history.dataclasses.NotingEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionHistoryDataStore @Inject constructor(
    @SessionDB private val db: SessionDatabase,
) {
    private val inputDelayDao = db.getInputDelayDao()
    private val notingEventDao = db.getNotingEventDao()
    private val sessionDao = db.getSessionDao()

    val numEventsInDB: Flow<Int> = notingEventDao.countEvents()
    val numOfSesions: Flow<Int> = sessionDao.countSessions()
    val numOfDays: Flow<Int> = notingEventDao.countEventsPerDay().map { it.size }

    suspend fun createSessionResource(): SessionResource {
        return SessionResource.create(sessionDao = sessionDao)
    }

    suspend fun storeOrReplaceNotingEvent(labelEvent: SHFLabelEvent, sessionId: Long) {
        notingEventDao.insertOrReplace(NotingEntry.from(labelEvent = labelEvent, sessionId = sessionId))
    }

    suspend fun storeOrReplaceInputDelayEvent(inputDelayEvent: InputDelayEvent, sessionId: Long) {
        inputDelayDao.insertOrReplace(InputDelayEntry.from(inputDelayEvent = inputDelayEvent, sessionId = sessionId))
    }
}
