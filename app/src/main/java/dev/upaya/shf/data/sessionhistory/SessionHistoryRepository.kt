package dev.upaya.shf.data.sessionhistory

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.IoDispatcher
import dev.upaya.shf.data.input.InputKey
import dev.upaya.shf.data.input.KeyPressDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


private const val DB_NAME = "dev.upaya.shf.session_db"


@Singleton
class SessionHistoryRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val keyPressDataSource: KeyPressDataSource,
) {

    private val db = Room.databaseBuilder(
        appContext,
        SessionDatabase::class.java,
        DB_NAME
    ).build()

    private val notingEventDao = db.getNotingEventDao()

    fun startRecording(scope: CoroutineScope) {

        // Drop current state of StateFlow
        val eventFlow = keyPressDataSource.inputKeyDown.drop(1)

        scope.launch {
            eventFlow.collect { event ->
                storeNotingEvent(inputKey = event.inputKey)
            }
        }
    }

    private suspend fun storeNotingEvent(inputKey: InputKey) {
        if (inputKey == InputKey.UNMAPPED)
            return
        val label = SHFLabelMap.getLabel(inputKey) ?: return
        notingEventDao.insert(NotingEvent(label = label))
    }

}
