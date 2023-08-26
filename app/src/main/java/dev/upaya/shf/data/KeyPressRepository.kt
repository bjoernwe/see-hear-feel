package dev.upaya.shf.data

import dev.upaya.shf.data.sources.DelayedInputEventDataSource
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.InputEventDataSource
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.sources.InputKeyMapping
import dev.upaya.shf.data.sources.IntEvent
import dev.upaya.shf.data.sources.KeyPressDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KeyPressRepository @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
    keyEventDataSource: InputEventDataSource,
    private val delayedInputEventDataSource: DelayedInputEventDataSource,
) {

    private val _keyCapturingIsEnabled = MutableStateFlow(false)
    private val keyCapturingIsEnabled: StateFlow<Boolean> = _keyCapturingIsEnabled

    val keyDown: SharedFlow<InputKey> = keyPressDataSource.inputKeyDown
    //val keyUp: SharedFlow<InputKey> = keyPressDataSource.inputKeyUp
    val inputEvent: Flow<InputEvent> = keyEventDataSource.keyDownEvent

    internal fun enableKeyCapturing(keyCapturingIsEnabled: Boolean) {
        _keyCapturingIsEnabled.value = keyCapturingIsEnabled
    }

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {

        if (!keyCapturingIsEnabled.value)
            return false

        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {

        if (!keyCapturingIsEnabled.value)
            return false

        return keyPressDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun registerKeyDownFromBackground(keyCode: Int): Boolean {

        if (!keyCapturingIsEnabled.value)
            return false

        return keyPressDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromBackground(keyCode: Int): Boolean {

        if (!keyCapturingIsEnabled.value)
            return false

        return keyPressDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun getDelayedInputEvent(scope: CoroutineScope): StateFlow<IntEvent> {
        return delayedInputEventDataSource.getDelayedInputEvent(externalScope = scope)
    }
}


private fun KeyPressDataSource.registerKeyDown(keyCode: Int): Boolean {

    val inputKey = InputKeyMapping.getInputKey(keyCode)

    if (inputKey == InputKey.UNMAPPED)
        return false

    registerKeyDown(inputKey)

    return true
}


private fun KeyPressDataSource.registerKeyUp(keyCode: Int): Boolean {

    val inputKey = InputKeyMapping.getInputKey(keyCode)

    if (inputKey == InputKey.UNMAPPED)
        return false

    registerKeyUp(inputKey)

    return true
}
