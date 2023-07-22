package dev.upaya.shf.inputs.key_press_states

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class KeyPressStateViewModel @Inject constructor(
    keyPressStateSource: KeyPressStateSource,
) : ViewModel() {
    val keyPressStates: StateFlow<KeyPressStates> = keyPressStateSource.keyPressStates
}
