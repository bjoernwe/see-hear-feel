package dev.upaya.shf.inputs

import dev.upaya.shf.DefaultDispatcher
import dev.upaya.shf.ui.asSharedFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventSource @Inject constructor(
    inputKeySource: InputKeySource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) {
    val inputEvent: SharedFlow<InputEvent> = inputKeySource.inputKeyDown
        .transform { inputKey ->
            emit(InputEvent(inputKey))
        }
        .asSharedFlow(CoroutineScope(dispatcher))
}
