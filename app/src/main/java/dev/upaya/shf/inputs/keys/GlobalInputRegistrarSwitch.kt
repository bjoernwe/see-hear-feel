package dev.upaya.shf.inputs.keys

import dev.upaya.shf.inputs.permissions.IBooleanSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * A global switch for turning off the [IInputKeyRegistrar] implementations.
 *
 * Reading the device's input keys is a tricky business where several things can go wrong. For
 * instance, we may break important functionality that's attached to hardware keys like volume
 * up/down. Or we may even break other apps when we continue to catch key inputs while the app is
 * running in background. Therefore this class serves as a central place for switching off all
 * key capturing when not needed for a running session.
 */
@Singleton
class GlobalInputRegistrarSwitch @Inject constructor() : IBooleanSource {

    private val _isEnabled = MutableStateFlow(false)
    override val isEnabled: StateFlow<Boolean> = _isEnabled

    fun switchOn() {
        _isEnabled.value = true
    }

    fun switchOff() {
        _isEnabled.value = false
    }
}