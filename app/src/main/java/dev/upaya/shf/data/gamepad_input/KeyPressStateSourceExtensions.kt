package dev.upaya.shf.data.gamepad_input

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date


internal fun MutableStateFlow<KeyPressStates>.addStateFor(gamepadKey: GamepadKey) {
    this.value = this.value.addStateFor(gamepadKey = gamepadKey)
}


private fun KeyPressStates.addStateFor(gamepadKey: GamepadKey): KeyPressStates {
    return this
        .toMutableMap()
        .apply { this[gamepadKey] = Date() }
        .toMap()
}


internal fun MutableStateFlow<KeyPressStates>.removeStateFor(gamepadKey: GamepadKey) {
    this.value = this.value.removeStateFor(gamepadKey = gamepadKey)
}


private fun KeyPressStates.removeStateFor(gamepadKey: GamepadKey): KeyPressStates {
    return this
        .toMutableMap()
        .apply { remove(gamepadKey) }
        .toMap()
}
