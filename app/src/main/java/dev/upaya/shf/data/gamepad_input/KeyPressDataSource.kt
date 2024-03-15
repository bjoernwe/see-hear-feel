package dev.upaya.shf.data.gamepad_input

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KeyPressDataSource @Inject constructor() {

    private val _gamepadKeyDown = MutableStateFlow(GamepadKeyEvent.ZERO)
    private val _gamepadKeyUp = MutableStateFlow(GamepadKeyEvent.ZERO)
    val inputKeyDown: StateFlow<GamepadKeyEvent> = _gamepadKeyDown
    val inputKeyUp: StateFlow<GamepadKeyEvent> = _gamepadKeyUp

    fun registerKeyDown(keyCode: Int): Boolean {

        if (!isSubscribedTo())
            return false

        val inputKey = InputKeyMapping.getInputKey(keyCode, allowUnmapped = false) ?: return false

        val keyNotReleasedYet = inputKeyUp.value.date.epochSecond < inputKeyDown.value.date.epochSecond

        if (keyNotReleasedYet)
            return true

        _gamepadKeyDown.tryEmit(GamepadKeyEvent(inputKey))

        return true
    }

    fun registerKeyUp(keyCode: Int): Boolean {

        if (!isSubscribedTo())
            return false

        val inputKey = InputKeyMapping.getInputKey(keyCode, allowUnmapped = false) ?: return false

        _gamepadKeyUp.tryEmit(GamepadKeyEvent(inputKey))

        return true
    }

    private fun isSubscribedTo(): Boolean {
        val subscribersToKeyDown = _gamepadKeyDown.subscriptionCount.value != 0
        val subscribersToKeyUp = _gamepadKeyUp.subscriptionCount.value != 0
        return subscribersToKeyDown || subscribersToKeyUp
    }
}
