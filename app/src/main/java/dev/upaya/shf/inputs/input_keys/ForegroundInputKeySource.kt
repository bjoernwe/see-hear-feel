package dev.upaya.shf.inputs.input_keys

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ForegroundInputKeySource @Inject constructor() : IInputKeySource, IInputKeyRegistrar {

    private var isEnabled = false

    private val _inputKeyDown = MutableSharedFlow<InputKey>(
        replay = 1, // behavior similar to StateFlow
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _inputKeyUp = MutableSharedFlow<InputKey>(
        replay = 1, // behavior similar to StateFlow
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val inputKeyDown: SharedFlow<InputKey> = _inputKeyDown
    override val inputKeyUp: SharedFlow<InputKey> = _inputKeyUp

    override fun registerKeyDown(keyCode: Int): Boolean {

        if (!isEnabled)
            return false

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        _inputKeyDown.tryEmit(inputKey)
        return true
    }

    override fun registerKeyUp(keyCode: Int): Boolean {

        if (!isEnabled)
            return false

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (inputKey == InputKey.UNMAPPED)
            return false

        _inputKeyUp.tryEmit(inputKey)
        return true
    }

    override fun enableRegistrar() {
        isEnabled = true
    }

    override fun disableRegistrar() {
        isEnabled = false
    }

}
