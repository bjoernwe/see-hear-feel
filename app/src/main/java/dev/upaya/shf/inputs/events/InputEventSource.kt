package dev.upaya.shf.inputs.events

import dev.upaya.shf.inputs.keys.GlobalInputKeySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventSource @Inject constructor(
    inputKeySource: GlobalInputKeySource,
) {
    val inputEvent: Flow<InputEvent> = inputKeySource.inputKeyDown.transform { emit(InputEvent(it)) }
}
