package dev.upaya.shf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.gamepad_input.KeyPressStates
import dev.upaya.shf.data.gamepad_input.KeyPressStatesDataSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class KeyPressStateViewModel @Inject constructor(
    keyPressStatesDataSource: KeyPressStatesDataSource,
) : ViewModel() {
    val keyPressStates: StateFlow<KeyPressStates> = keyPressStatesDataSource.getKeyPressStates(scope = viewModelScope)
}
