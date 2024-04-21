package dev.upaya.shf.data.session_data.datastore

import dev.upaya.shf.data.session_data.datastore.daos.NotingEventDao
import dev.upaya.shf.data.session_data.datastore.daos.SessionDao
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.Closeable
import java.time.OffsetDateTime


/**
 * Auto-closable resource for a session. When the resource is closed, the session's end timestamp is
 * set to the current time.
 */
class SessionResource(
    val sessionId: Long,
    private val sessionDao: SessionDao,
    private val notingEventDao: NotingEventDao,
) : AutoCloseable, Closeable {

    companion object {
        suspend fun create(
            sessionDao: SessionDao,
            notingEventDao: NotingEventDao,
        ): SessionResource {
            val session = SessionEntry(start = OffsetDateTime.now())
            val sessionId = sessionDao.insertOrReplace(session = session)
            return SessionResource(
                sessionId = sessionId,
                sessionDao = sessionDao,
                notingEventDao = notingEventDao,
            )
        }
    }

    override fun close() {
        closeSessionOrDeleteIfEmpty()
    }

    private fun closeSessionOrDeleteIfEmpty() {
        CoroutineScope(Dispatchers.IO).launch {
            val numEvents = notingEventDao.countEventsOfCurrentSession().firstOrNull()
            if (numEvents == null || numEvents == 0) {
                sessionDao.deleteSession(id = sessionId)
            } else {
                sessionDao.setEndTimestamp(id = sessionId)
            }
        }
    }
}
