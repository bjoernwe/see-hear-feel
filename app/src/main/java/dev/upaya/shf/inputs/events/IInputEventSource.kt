package dev.upaya.shf.inputs.events

import kotlinx.coroutines.flow.StateFlow


interface IInputEventSource {
    val inputEvent: StateFlow<InputEvent>
}
