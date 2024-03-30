package dev.upaya.shf.data.session_data.datastore

import dev.upaya.shf.data.session_data.datastore.daos.SessionDao
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
) : AutoCloseable, Closeable {

    companion object {
        suspend fun create(sessionDao: SessionDao): SessionResource {
            val session = SessionEntry(start = OffsetDateTime.now())
            val sessionId = sessionDao.insertOrReplace(session = session)
            return SessionResource(
                sessionId = sessionId,
                sessionDao = sessionDao,
            )
        }
    }

    override fun close() {
        CoroutineScope(Dispatchers.IO).launch {
            sessionDao.setEndTimestamp(id = sessionId)
        }
    }
}
