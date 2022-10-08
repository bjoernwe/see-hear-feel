package dev.upaya.shf.inputs

import android.view.KeyEvent.*


class InputDeviceHeadset {

    companion object : InputDevice  {

        override fun getInputKey(keyCode: Int): InputKey {
            return when (keyCode) {
                KEYCODE_VOLUME_UP -> InputKey.KEY_A
                KEYCODE_VOLUME_DOWN -> InputKey.KEY_B
                KEYCODE_HEADSETHOOK -> InputKey.KEY_X
                KEYCODE_A -> InputKey.KEY_A
                KEYCODE_B -> InputKey.KEY_B
                KEYCODE_X -> InputKey.KEY_X
                else -> InputKey.UNMAPPED
            }
        }

    }

}
