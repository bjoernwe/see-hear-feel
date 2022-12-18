package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<InputKey, Date?>


@Singleton
class InputEventSource @Inject constructor() {

    private val inputDevice: InputDevice = InputDeviceGeneric

    private val _inputEvent: MutableStateFlow<InputEvent> = MutableStateFlow(InputEvent(InputKey.KEY_A))
    val inputEvent: StateFlow<InputEvent> = _inputEvent

    private val _keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())
    val keyPressStates: StateFlow<KeyPressStates> = _keyPressStates

    fun keyDown(keyCode: Int): Boolean {

        val inputKey = inputDevice.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        if (_keyPressStates.value[inputKey] == null)
            _inputEvent.value = InputEvent(inputKey = inputKey)

        _keyPressStates.value = _keyPressStates.value.toMutableMap().apply {
            this[inputKey] = Date()
        }

        return true
    }

    fun keyUp(keyCode: Int): Boolean {

        val inputKey = inputDevice.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        _keyPressStates.value = _keyPressStates.value.toMutableMap().apply {
            this[inputKey] = null
        }

        return true
    }

}
