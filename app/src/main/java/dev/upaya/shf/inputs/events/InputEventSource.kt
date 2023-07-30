package dev.upaya.shf.inputs.events

import dev.upaya.shf.inputs.DefaultDispatcher
import dev.upaya.shf.inputs.keys.GlobalKeySource
import dev.upaya.shf.inputs.keys.IInputKeySource
import dev.upaya.shf.inputs.keys.InputKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventSource @Inject constructor(
    @GlobalKeySource inputKeySource: IInputKeySource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) : IInputEventSource {

    private val _inputEvent = MutableStateFlow(InputEvent(InputKey.UNMAPPED))
    override val inputEvent: StateFlow<InputEvent> = _inputEvent

    init {
        CoroutineScope(dispatcher).launch {
            inputKeySource.inputKeyDown.collect { _inputEvent.value = InputEvent(it) }
        }
    }

}
