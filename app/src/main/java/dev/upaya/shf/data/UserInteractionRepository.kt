package dev.upaya.shf.data

import dev.upaya.shf.data.sources.DelayedInputEventDataSource
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.InputEventDataSource
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.sources.IntEvent
import dev.upaya.shf.data.sources.KeyPressDataSource
import dev.upaya.shf.data.sources.PreferencesDataSource
import dev.upaya.shf.data.sources.SessionStateDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combineTransform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInteractionRepository @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
    keyEventDataSource: InputEventDataSource,
    private val delayedInputEventDataSource: DelayedInputEventDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val sessionStateDataSource: SessionStateDataSource,
) {

    private val _keyLoggingIsEnabled = MutableStateFlow(false)
    private val keyLoggingIsEnabled: StateFlow<Boolean> = _keyLoggingIsEnabled

    val keyDown: SharedFlow<InputKey> = keyPressDataSource.inputKeyDown
    //val keyUp: SharedFlow<InputKey> = keyPressDataSource.inputKeyUp
    val inputEvent: Flow<InputEvent> = keyEventDataSource.keyDownEvent

    internal fun enableKeyLogging(keyLoggingIsEnabled: Boolean) {
        _keyLoggingIsEnabled.value = keyLoggingIsEnabled
    }

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {

        if (!keyLoggingIsEnabled.value)
            return false

        if (sessionStateDataSource.isBackgroundSession.value)
            return false

        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {

        if (!keyLoggingIsEnabled.value)
            return false

        if (sessionStateDataSource.isBackgroundSession.value)
            return false

        return keyPressDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun registerKeyDownFromBackground(keyCode: Int): Boolean {

        if (!keyLoggingIsEnabled.value)
            return false

        if (!sessionStateDataSource.isBackgroundSession.value)
            return false

        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromBackground(keyCode: Int): Boolean {

        if (!keyLoggingIsEnabled.value)
            return false

        if (!sessionStateDataSource.isBackgroundSession.value)
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
}