package dev.upaya.shf.app.settings

import dev.upaya.shf.data.sources.IBooleanSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MockBooleanSource(initialValue: Boolean = false) : IBooleanSource {

    private val _isEnabled = MutableStateFlow(initialValue)
    override val isEnabled: StateFlow<Boolean> = _isEnabled

    fun setValue(newValue: Boolean) {
        _isEnabled.value = newValue
    }

}
