package dev.upaya.shf.data.session_data.datastore

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.datastore.daos.NotingEventDao
import dev.upaya.shf.data.session_data.datastore.dataclasses.NotingEntry
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.OffsetDateTime


class SessionDatabaseTest {

    private lateinit var notingEventDao: NotingEventDao
    private lateinit var db: SessionDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SessionDatabase::class.java).build()
        notingEventDao = db.getNotingEventDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun sessionDatabase_eventIsStored_andLoadedAgain() = runTest {

        // GIVEN a noting event
        val storedEvent = NotingEntry(id = 1, label = SHFLabel.GONE)

        // WHEN the event is stored in DB and loaded again
        notingEventDao.insertOrReplace(storedEvent)
        val loadedEvents = notingEventDao.loadAllNotingEvents()
        val loadedEvent = loadedEvents[0]

        // THEN the result is exactly the stored event
        assert(loadedEvents.size == 1)
        assert(loadedEvent == storedEvent)
    }

    @Test
    fun sessionDatabase_countEvents_countsEvents() = runTest {

        // GIVEN an empty DB with noting events
        val numEventsBefore = notingEventDao.countEvents().take(1).toList()[0]

        // WHEN an event is inserted
        notingEventDao.insertOrReplace(NotingEntry(id = 1, label = SHFLabel.GONE))

        // THEN countEvents() is updated
        val after = notingEventDao.countEvents().take(1).toList()[0]
        assertEquals(0, numEventsBefore)
        assertEquals(1, after)
    }

    @Test
    fun sessionDatabase_insertOrReplace_isIdempotent() = runTest {

        // GIVEN an empty DB with noting events
        // WHEN the same event is inserted multiple times
        val event = NotingEntry(id = 1, label = SHFLabel.GONE)
        notingEventDao.insertOrReplace(event)
        notingEventDao.insertOrReplace(event)
        notingEventDao.insertOrReplace(event)

        // THEN it is only stored unce
        val numOfStoredEvents = notingEventDao.countEvents().take(1).toList()[0]
        assertEquals(1, numOfStoredEvents)
    }

    @Test
    fun sessionDatabase_notingsPerDay_areCorrectlySummarized() = runTest {

        // GIVEN a database with three noting events on two days
        notingEventDao.insertOrReplace(NotingEntry(id = 1, label = SHFLabel.GONE, date = OffsetDateTime.parse("2000-01-01T00:00:00+00:00")))
        notingEventDao.insertOrReplace(NotingEntry(id = 2, label = SHFLabel.GONE, date = OffsetDateTime.parse("2000-01-01T00:00:00+00:00")))
        notingEventDao.insertOrReplace(NotingEntry(id = 3, label = SHFLabel.GONE, date = OffsetDateTime.parse("2000-01-02T00:00:00+00:00")))

        // WHEN the notings per day are queried
        val notingsPerDay = notingEventDao.countEventsPerDay().take(1).toList()[0]

        // THEN they are correctly summarized
        assertEquals(2, notingsPerDay[0].count)
        assertEquals(1, notingsPerDay[1].count)
    }
}
