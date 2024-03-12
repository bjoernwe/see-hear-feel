package dev.upaya.shf.data.input

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date


internal fun MutableStateFlow<KeyPressStates>.addStateFor(inputKey: InputKey) {
    this.value = this.value.addStateFor(inputKey = inputKey)
}


private fun KeyPressStates.addStateFor(inputKey: InputKey): KeyPressStates {
    return this
        .toMutableMap()
        .apply { this[inputKey] = Date() }
        .toMap()
}


internal fun MutableStateFlow<KeyPressStates>.removeStateFor(inputKey: InputKey) {
    this.value = this.value.removeStateFor(inputKey = inputKey)
}


private fun KeyPressStates.removeStateFor(inputKey: InputKey): KeyPressStates {
    return this
        .toMutableMap()
        .apply { remove(inputKey) }
        .toMap()
}
