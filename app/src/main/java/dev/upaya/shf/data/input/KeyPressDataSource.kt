package dev.upaya.shf.data.input

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KeyPressDataSource @Inject constructor() {

    private val _inputKeyDown = MutableStateFlow(InputEvent(InputKey.UNMAPPED))
    private val _inputKeyUp = MutableStateFlow(InputEvent(InputKey.UNMAPPED))
    val inputKeyDown: StateFlow<InputEvent> = _inputKeyDown
    val inputKeyUp: StateFlow<InputEvent> = _inputKeyUp

    fun registerKeyDown(keyCode: Int): Boolean {

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (!isSubscribedTo())
            return false

        if (inputKey == InputKey.UNMAPPED)
            return false

        val keyNotReleasedYet = inputKeyUp.value.date.before(inputKeyDown.value.date)

        if (keyNotReleasedYet)
            return true

        _inputKeyDown.tryEmit(InputEvent(inputKey))

        return true
    }

    fun registerKeyUp(keyCode: Int): Boolean {

        val inputKey = InputKeyMapping.getInputKey(keyCode)

        if (!isSubscribedTo())
            return false

        if (inputKey == InputKey.UNMAPPED)
            return false

        _inputKeyUp.tryEmit(InputEvent(inputKey))

        return true
    }

    private fun isSubscribedTo(): Boolean {
        val subscribersToKeyDown = _inputKeyDown.subscriptionCount.value != 0
        val subscribersToKeyUp = _inputKeyUp.subscriptionCount.value != 0
        return subscribersToKeyDown || subscribersToKeyUp
    }
}
