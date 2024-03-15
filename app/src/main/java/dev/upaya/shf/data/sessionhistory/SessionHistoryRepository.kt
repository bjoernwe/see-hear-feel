package dev.upaya.shf.data.sessionhistory

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.input.GamepadKey
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
                storeNotingEvent(gamepadKey = event.gamepadKey)
            }
        }
    }

    private suspend fun storeNotingEvent(gamepadKey: GamepadKey) {
        if (gamepadKey == GamepadKey.UNMAPPED)
            return
        val label = SHFLabelMap.getLabel(gamepadKey) ?: return
        notingEventDao.insert(NotingEvent(label = label))
    }

}
