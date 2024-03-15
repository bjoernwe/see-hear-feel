package dev.upaya.shf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.gamepad.KeyPressStates
import dev.upaya.shf.data.gamepad.GamepadPressStateDataSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class KeyPressStateViewModel @Inject constructor(
    gamepadPressStateDataSource: GamepadPressStateDataSource,
) : ViewModel() {
    val keyPressStates: StateFlow<KeyPressStates> = gamepadPressStateDataSource.getKeyPressStates(scope = viewModelScope)
}
