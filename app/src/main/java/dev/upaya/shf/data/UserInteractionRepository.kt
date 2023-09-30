package dev.upaya.shf.data

import dev.upaya.shf.data.sources.AppState
import dev.upaya.shf.data.sources.AppStateDataSource
import dev.upaya.shf.data.sources.DelayedInputEventDataSource
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.InputEventDataSource
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.sources.IntEvent
import dev.upaya.shf.data.sources.KeyPressDataSource
import dev.upaya.shf.data.sources.PreferencesDataSource
import dev.upaya.shf.data.sources.SessionState
import dev.upaya.shf.data.sources.SessionStateDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInteractionRepository @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
    keyEventDataSource: InputEventDataSource,
    private val delayedInputEventDataSource: DelayedInputEventDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val sessionStateDataSource: SessionStateDataSource,
    private val appStateDataSource: AppStateDataSource,
) {

    val keyDown: SharedFlow<InputKey> = keyPressDataSource.inputKeyDown
    //val keyUp: SharedFlow<InputKey> = keyPressDataSource.inputKeyUp
    val inputEvent: Flow<InputEvent> = keyEventDataSource.keyDownEvent

    val vibrationFromForeground: Flow<Boolean> = sessionStateDataSource.sessionState
        .combine(appStateDataSource.appState) { sessionState, appState ->
            sessionState == SessionState.RUNNING_IN_FOREGROUND && appState == AppState.RUNNING
        }

    val backgroundNotificationServiceEnabled: Flow<Boolean> = sessionStateDataSource.sessionState
        .transform { sessionState ->
            emit(sessionState == SessionState.RUNNING_IN_BACKGROUND)
        }

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {
        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {
        return keyPressDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun registerKeyDownFromBackground(keyCode: Int): Boolean {

        // There's no legit reason to capture keys from background unless for a running session!
        if (sessionStateDataSource.sessionState.value != SessionState.RUNNING_IN_BACKGROUND)
            return false

        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromBackground(keyCode: Int): Boolean {

        if (sessionStateDataSource.sessionState.value != SessionState.RUNNING_IN_BACKGROUND)
            return false

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

    fun registerAppStartEvent() {
        appStateDataSource.registerStartEvent()
    }

    fun registerAppResumeEvent() {
        appStateDataSource.registerResumeEvent()
    }

    fun registerAppPauseEvent() {
        appStateDataSource.registerPauseEvent()
    }

    fun registerAppStopEvent() {
        appStateDataSource.registerStopEvent()
    }

}
