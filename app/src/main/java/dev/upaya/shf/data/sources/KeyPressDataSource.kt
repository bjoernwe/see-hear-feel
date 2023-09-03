package dev.upaya.shf.data.sources

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KeyPressDataSource @Inject constructor() {

    // We are using SharedFlow here instead of StateFlow because the same key in a row should
    // re-emit from the flow (doesn't work with StateFlow)
    private val _inputKeyDown = MutableSharedFlow<InputKey>(
        replay = 1, // behavior similar to StateFlow
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _inputKeyUp = MutableSharedFlow<InputKey>(
        replay = 1, // behavior similar to StateFlow
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val inputKeyDown: SharedFlow<InputKey> = _inputKeyDown
    val inputKeyUp: SharedFlow<InputKey> = _inputKeyUp

    fun registerKeyDown(keyCode: Int): Boolean {

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        registerKeyDown(inputKey)

        return true
    }

    fun registerKeyUp(keyCode: Int): Boolean {

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        registerKeyUp(inputKey)

        return true
    }

    private fun registerKeyDown(inputKey: InputKey) {
        _inputKeyDown.tryEmit(inputKey)
    }

    private fun registerKeyUp(inputKey: InputKey) {
        _inputKeyUp.tryEmit(inputKey)
    }
}
