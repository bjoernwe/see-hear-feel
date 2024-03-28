package dev.upaya.shf.data.session_history

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.upaya.shf.data.session_history.daos.InputDelayEventDao
import dev.upaya.shf.data.session_history.daos.NotingEventDao
import dev.upaya.shf.data.session_history.daos.SessionDao
import dev.upaya.shf.data.session_history.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_history.dataclasses.NotingEntry
import dev.upaya.shf.data.session_history.dataclasses.SessionEntry


@Database(
    version = 3,
    entities = [
        InputDelayEntry::class,
        NotingEntry::class,
        SessionEntry::class,
    ],
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3),
    ]
)
@TypeConverters(Converters::class)
abstract class SessionDatabase : RoomDatabase() {
    abstract fun getInputDelayDao(): InputDelayEventDao
    abstract fun getNotingEventDao(): NotingEventDao
    abstract fun getSessionDao(): SessionDao
}
