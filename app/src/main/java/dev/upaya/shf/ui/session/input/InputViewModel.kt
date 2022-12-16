package dev.upaya.shf.ui.session.input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.*
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class InputViewModel @Inject constructor(
    inputEventSource: InputEventSource,
) : ViewModel() {
    val inputEvent: StateFlow<InputEvent?> = inputEventSource.inputEvent
    val keyPressStates: StateFlow<KeyPressStates> = inputEventSource.keyPressStates
}
