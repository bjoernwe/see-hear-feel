package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventSource @Inject constructor() {

    private val inputDevice: InputDevice = InputDeviceHeadset
    private val _inputEvent: MutableStateFlow<InputEvent?> = MutableStateFlow(null)
    val inputEvent: StateFlow<InputEvent?> = _inputEvent

    fun resetInputEvent() {
        _inputEvent.value = null
    }

    fun updateInputKey(keyCode: Int): Boolean {
        val inputKey = inputDevice.getInputKey(keyCode) ?: return false
        _inputEvent.value = InputEvent(inputKey = inputKey)
        return true
    }

}
