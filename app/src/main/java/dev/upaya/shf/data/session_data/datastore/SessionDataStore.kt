package dev.upaya.shf.data.session_data.datastore

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_data.Session
import dev.upaya.shf.data.session_data.datastore.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


/*
 * Design decision: Only do trivial calculations here. Put more sophisticated calculations in the
 * repositories, like SessionStatsRepository.
 */
@Singleton
class SessionDataStore @Inject constructor(
    @SessionDB private val db: SessionDatabase,
) {
    private val inputDelayDao = db.getInputDelayDao()
    private val notingEventDao = db.getNotingEventDao()
    private val sessionDao = db.getSessionDao()

    val numEventsInDB: Flow<Int> = notingEventDao.countEvents()
    val numEventsOfCurrentSession: Flow<Int> = notingEventDao.countEventsOfCurrentSession()
    val numOfSesions: Flow<Int> = sessionDao.countSessions()
    val numOfNotingsPerDay = notingEventDao.countEventsPerDay()
    val numOfDays: Flow<Int> = notingEventDao.countEventsPerDay().map { it.size }
    val newestSession: Flow<Session> = sessionDao.getNewestSession().map { it.toSession() }
    val labelFreqs: Flow<Map<SHFLabel, Int>> = notingEventDao.getEventsPerLabelForCurrentSession().map {
        it.mapKeys { entry -> SHFLabel.valueOf(entry.key) }
    }

    suspend fun createSessionResource(): SessionResource {
        return SessionResource.create(sessionDao = sessionDao, notingEventDao = notingEventDao)
    }

    suspend fun storeOrReplaceNotingEvent(labelEvent: SHFLabelEvent, sessionId: Long) {
        notingEventDao.insertOrReplace(NotingEntry.from(labelEvent = labelEvent, sessionId = sessionId))
    }

    suspend fun storeOrReplaceInputDelayEvent(inputDelayEvent: InputDelayEvent, sessionId: Long) {
        inputDelayDao.insertOrReplace(InputDelayEntry.from(inputDelayEvent = inputDelayEvent, sessionId = sessionId))
    }
}
