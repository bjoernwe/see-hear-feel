package dev.upaya.shf.data.session_history

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.upaya.shf.data.session_history.daos.InputDelayEventDao
import dev.upaya.shf.data.session_history.daos.NotingEventDao
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_history.dataclasses.NotingEntry


@Database(
    version = 2,
    entities = [NotingEntry::class, InputDelayEntry::class],
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
