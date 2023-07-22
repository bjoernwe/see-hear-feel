package dev.upaya.shf.background.settings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MockBooleanSource(initialValue: Boolean = false) : IBooleanSource {

    private val _value = MutableStateFlow(initialValue)
    override val value: StateFlow<Boolean> = _value

    fun setValue(newValue: Boolean) {
        _value.value = newValue
    }

}
