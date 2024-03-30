package dev.upaya.shf.data.session_data.datastore

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


private const val DB_NAME = "dev.upaya.shf.session_db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @SessionDB
    fun providesSessionDatabase(
        @ApplicationContext appContext: Context,
    ): SessionDatabase {
        return Room.databaseBuilder(
            appContext,
            SessionDatabase::class.java,
            DB_NAME
        ).build()
    }
}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SessionDB
