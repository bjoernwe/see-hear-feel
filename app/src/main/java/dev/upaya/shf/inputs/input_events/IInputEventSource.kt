package dev.upaya.shf.inputs.input_events

import kotlinx.coroutines.flow.SharedFlow


interface IInputEventSource {
    val inputEvent: SharedFlow<InputEvent>
}
