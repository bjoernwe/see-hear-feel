package dev.upaya.shf.data.session_data.datastore.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.datastore.SessionDatabase
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.OffsetDateTime


class SessionDaoTest {

    private lateinit var db: SessionDatabase
    private lateinit var unitUnderTest: SessionDao
    private lateinit var notingEventDao: NotingEventDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SessionDatabase::class.java).build()
        unitUnderTest = db.getSessionDao()
        notingEventDao = db.getNotingEventDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun sessionDao_setEndTimestamp_updatesEndTimestamp() = runTest {

        // GIVEN a SessionDao with a newly created session
        val sessionId = unitUnderTest.insertOrReplace(SessionEntry(start = OffsetDateTime.now()))

        // WHEN the session end is updated
        unitUnderTest.setEndTimestamp(sessionId)

        // THEN the session end is not null anymore
        val session = db.getSessionDao().getSession(sessionId)
        Assert.assertNotNull(session.end)
    }

    @Test
    fun sessionDao_getLatestSessionWithEvents() = runTest {

        // GIVEN a DB with a session and three events
        val sessionId = unitUnderTest.insertOrReplace(SessionEntry(id = 42, start = OffsetDateTime.now()))
        notingEventDao.insertOrReplace(NotingEntry(sessionId = sessionId, label = SHFLabel.SEE))
        notingEventDao.insertOrReplace(NotingEntry(sessionId = sessionId, label = SHFLabel.HEAR))
        notingEventDao.insertOrReplace(NotingEntry(sessionId = sessionId, label = SHFLabel.FEEL))

        // WHEN the session is queried together with the events
        val sessionWithEvents = unitUnderTest.getLatestSessionWithEvents().first()

        // THEN the result contains all events
        Assert.assertEquals(42, sessionWithEvents.session.id)
        Assert.assertEquals(3, sessionWithEvents.notings.size)
    }
}
