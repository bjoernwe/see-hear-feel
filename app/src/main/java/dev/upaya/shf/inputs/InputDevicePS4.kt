package dev.upaya.shf.inputs

import android.view.KeyEvent.*


class InputDevicePS4 {

    companion object : InputDevice  {

        override fun getInputKey(keyCode: Int): InputKey {
            return when (keyCode) {
                KEYCODE_DPAD_LEFT -> InputKey.KEY_1
                KEYCODE_DPAD_RIGHT -> InputKey.KEY_2
                KEYCODE_DPAD_UP -> InputKey.KEY_3
                KEYCODE_DPAD_DOWN -> InputKey.KEY_4
                else -> InputKey.UNDEFINED
            }
        }

    }

}
