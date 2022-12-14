package dev.upaya.shf.ui.controller

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.*
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ControllerSetupViewModel @Inject constructor(
    inputEventSource: InputEventSource,
) : ViewModel() {
    val keyPressStates: StateFlow<KeyPressStates> = inputEventSource.keyPressStates
}
