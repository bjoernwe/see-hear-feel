package dev.upaya.shf.data.session_history.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.upaya.shf.data.session_history.SessionDatabase
import dev.upaya.shf.data.session_history.dataclasses.SessionEntry
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.OffsetDateTime


class SessionDaoTests {

    private lateinit var db: SessionDatabase
    private lateinit var unitUnderTest: SessionDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SessionDatabase::class.java).build()
        unitUnderTest = db.getSessionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getSessionDao_setEndTimestamp_updatesEndTimestamp() = runTest {

        // GIVEN a SessionDao with a newly created session
        val sessionId = unitUnderTest.insertOrReplace(SessionEntry(start = OffsetDateTime.now()))

        // WHEN the session end is updated
        unitUnderTest.setEndTimestamp(sessionId)

        // THEN the session end is not null anymore
        val session = db.getSessionDao().getSession(sessionId)
        Assert.assertNotNull(session.end)
    }
}
