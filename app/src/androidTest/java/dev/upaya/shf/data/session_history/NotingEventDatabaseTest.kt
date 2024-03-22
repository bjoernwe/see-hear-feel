package dev.upaya.shf.data.session_history

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.upaya.shf.data.labels.SHFLabel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException


class NotingEventDatabaseTest {

    private lateinit var notingEventDao: NotingEventDao
    private lateinit var db: NotingEventDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NotingEventDatabase::class.java).build()
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
        val storedEvent = NotingEvent(id = 1, label = SHFLabel.GONE)

        // WHEN the event is stored in DB and loaded again
        notingEventDao.insert(storedEvent)
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
        notingEventDao.insert(NotingEvent(id = 1, label = SHFLabel.GONE))

        // THEN countEvents() is updated
        val after = notingEventDao.countEvents().take(1).toList()[0]
        assertEquals(0, numEventsBefore)
        assertEquals(1, after)
    }

}
