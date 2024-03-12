package dev.upaya.shf.data.sessionhistory

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [NotingEvent::class], version = 1)
@TypeConverters(Converters::class)
abstract class SessionDatabase : RoomDatabase() {
    abstract fun getNotingEventDao(): NotingEventDao
}
