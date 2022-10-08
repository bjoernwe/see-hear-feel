package dev.upaya.shf.inputs

import android.view.KeyEvent.*


class InputDevicePS4 {

    companion object : InputDevice  {

        override fun getInputKey(keyCode: Int): InputKey {
            return when (keyCode) {
                KEYCODE_DPAD_UP -> InputKey.KEY_UP
                KEYCODE_DPAD_DOWN -> InputKey.KEY_DOWN
                KEYCODE_DPAD_LEFT -> InputKey.KEY_LEFT
                KEYCODE_DPAD_RIGHT -> InputKey.KEY_RIGHT
                KEYCODE_BUTTON_A -> InputKey.KEY_A
                KEYCODE_BUTTON_B -> InputKey.KEY_B
                KEYCODE_BUTTON_X -> InputKey.KEY_X
                KEYCODE_BUTTON_Y -> InputKey.KEY_Y
                else -> InputKey.UNMAPPED
            }
        }

    }

}
