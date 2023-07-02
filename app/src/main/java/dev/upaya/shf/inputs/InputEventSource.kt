package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<InputKey, Date?>


@Singleton
class InputEventSource @Inject constructor(
    inputKeySource: InputKeySource,
) {
    val inputEvent: Flow<InputEvent> = inputKeySource.inputKeyDown.transform { emit(InputEvent(it)) }
}
