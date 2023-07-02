package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventSource @Inject constructor(
    inputKeySource: InputKeySource,
) {
    val inputEvent: Flow<InputEvent> = inputKeySource.inputKeyDown.transform { emit(InputEvent(it)) }
}
