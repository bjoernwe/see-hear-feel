package dev.upaya.shf.data

import dev.upaya.shf.data.sources.DelayedInputEventDataSource
import dev.upaya.shf.data.input.InputEvent
import dev.upaya.shf.data.sources.IntEvent
import dev.upaya.shf.data.input.KeyPressDataSource
import dev.upaya.shf.data.preferences.PreferencesDataSource
import dev.upaya.shf.data.sessionstate.SessionState
import dev.upaya.shf.data.sessionstate.SessionStateDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInteractionRepository @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
    private val delayedInputEventDataSource: DelayedInputEventDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    sessionStateDataSource: SessionStateDataSource,
) {

    val keyDown: StateFlow<InputEvent> = keyPressDataSource.inputKeyDown
    //val keyUp: StateFlow<InputEvent> = keyPressDataSource.inputKeyUp

    val vibrationFromForeground: Flow<Boolean> = sessionStateDataSource.sessionState
        .transform { sessionState ->
            emit(sessionState == SessionState.FOREGROUND_SESSION_RUNNING)
        }

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {
        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {
        return keyPressDataSource.registerKeyUp(keyCode = keyCode)
    }

    /**
     * Return a flow of delayed input events conditioned on pacing being enabled in settings.
     */
    fun getDelayedInputEvent(scope: CoroutineScope): Flow<IntEvent> {
        val delayedInputs = delayedInputEventDataSource.getDelayedInputEvent(externalScope = scope)
        val pacingEnabled = preferencesDataSource.isPacingEnabled
        return delayedInputs.combineTransform(pacingEnabled) { delayEvent, pacing ->
            if (pacing) emit(delayEvent)
        }
    }

}
