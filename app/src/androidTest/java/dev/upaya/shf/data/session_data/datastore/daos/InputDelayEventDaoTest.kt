package dev.upaya.shf.data.session_data.datastore.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.upaya.shf.data.session_data.datastore.SessionDatabase
import dev.upaya.shf.data.session_data.datastore.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_data.datastore.dataclasses.SessionEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.OffsetDateTime


class InputDelayEventDaoTest {

    private lateinit var db: SessionDatabase
    private lateinit var unitUnderTest: InputDelayEventDao
    private lateinit var sessionDao: SessionDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SessionDatabase::class.java).build()
        unitUnderTest = db.getInputDelayDao()
        sessionDao = db.getSessionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun inputDelayEventDao_getDelaysOfCurrentSession_returnsDelaysOfCurrentSession() = runTest {

        // GIVEN two sessions with delays
        sessionDao.insertOrReplace(SessionEntry(id = 1, start = OffsetDateTime.parse("2000-01-01T00:00:00+00:00")))
        unitUnderTest.insertOrReplace(InputDelayEntry(sessionId = 1, id = 1, delayInterval = 1, delaysInARow = 1))
        sessionDao.setEndTimestamp(id = 1, end = OffsetDateTime.parse("2000-01-01T00:01:00+00:00"))
        sessionDao.insertOrReplace(SessionEntry(id = 2, start = OffsetDateTime.parse("2000-01-02T00:00:00+00:00")))
        unitUnderTest.insertOrReplace(InputDelayEntry(sessionId = 2, id = 2, delayInterval = 1, delaysInARow = 1))
        unitUnderTest.insertOrReplace(InputDelayEntry(sessionId = 2, id = 3, delayInterval = 1, delaysInARow = 2))

        // WHEN delays are queried for most recent session
        val delays = unitUnderTest.getDelaysOfCurrentSession().first()

        // THEN only delays from the second session are returned
        Assert.assertEquals(2, delays.size)
        Assert.assertEquals(2, delays[0].id)
        Assert.assertEquals(3, delays[1].id)
    }
}
