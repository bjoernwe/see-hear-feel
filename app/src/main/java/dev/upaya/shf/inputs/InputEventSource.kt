package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<InputKey, Date?>


@Singleton
class InputEventSource @Inject constructor() {

    private val inputDevice: InputDevice = InputDeviceGeneric

    private val _inputEvent: MutableStateFlow<InputEvent?> = MutableStateFlow(null)
    val inputEvent: Flow<InputEvent> = _inputEvent.asFlow()

    private val _keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())
    val keyPressStates: StateFlow<KeyPressStates> = _keyPressStates

    fun registerKeyDown(keyCode: Int): Boolean {

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

    fun registerKeyUp(keyCode: Int): Boolean {

        val inputKey = inputDevice.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        _keyPressStates.value = _keyPressStates.value.toMutableMap().apply {
            this[inputKey] = null
        }

        return true
    }

}


private fun <T> StateFlow<T?>.asFlow(): Flow<T> {
    return this.transform { value ->
        if (value != null) {
            emit(value)
        }
    }
}
