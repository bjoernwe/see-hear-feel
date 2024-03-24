package dev.upaya.shf.data.session_history

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.upaya.shf.data.session_history.daos.InputDelayEventDao
import dev.upaya.shf.data.session_history.daos.NotingEventDao
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEvent
import dev.upaya.shf.data.session_history.dataclasses.NotingEvent


@Database(
    version = 2,
    entities = [NotingEvent::class, InputDelayEvent::class],
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
@TypeConverters(Converters::class)
abstract class NotingEventDatabase : RoomDatabase() {
    abstract fun getNotingEventDao(): NotingEventDao
    abstract fun getInputDelayDao(): InputDelayEventDao
}
