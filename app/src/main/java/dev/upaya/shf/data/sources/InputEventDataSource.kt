package dev.upaya.shf.data.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputEventDataSource @Inject constructor(
    keyPressDataSource: KeyPressDataSource,
) {
    val keyDownEvent: Flow<InputEvent> = keyPressDataSource.inputKeyDown.transform { emit(InputEvent(it)) }
    //val keyUpEvent: Flow<InputEvent> = keyPressDataSource.inputKeyUp.transform { emit(InputEvent(it)) }
}
