package dev.upaya.shf.inputs.input_events

import kotlinx.coroutines.flow.StateFlow


interface IInputEventSource {
    val inputEvent: StateFlow<InputEvent>
}
