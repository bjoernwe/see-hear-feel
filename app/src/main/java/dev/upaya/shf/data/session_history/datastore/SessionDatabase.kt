package dev.upaya.shf.data.session_history.datastore

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.upaya.shf.data.session_history.datastore.daos.InputDelayEventDao
import dev.upaya.shf.data.session_history.datastore.daos.NotingEventDao
import dev.upaya.shf.data.session_history.datastore.daos.SessionDao
import dev.upaya.shf.data.session_history.datastore.dataclasses.InputDelayEntry
import dev.upaya.shf.data.session_history.datastore.dataclasses.NotingEntry
import dev.upaya.shf.data.session_history.datastore.dataclasses.SessionEntry


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
