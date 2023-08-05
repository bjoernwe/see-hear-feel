package dev.upaya.shf.background.settings

import dev.upaya.shf.inputs.permissions.IBooleanSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MockBooleanSource(initialValue: Boolean = false) : IBooleanSource {

    private val _isEnabled = MutableStateFlow(initialValue)
    override val isEnabled: StateFlow<Boolean> = _isEnabled

    fun setValue(newValue: Boolean) {
        _isEnabled.value = newValue
    }

}
