package dev.upaya.shf.input_devices

import android.view.KeyEvent.*


class InputDeviceHeadset {

    companion object : InputDevice  {

        override fun getInputKey(keyCode: Int): InputKey? {
            return when (keyCode) {
                KEYCODE_VOLUME_UP -> InputKey.KEY_1
                KEYCODE_VOLUME_DOWN -> InputKey.KEY_2
                KEYCODE_HEADSETHOOK -> InputKey.KEY_3
                else -> null
            }
        }

    }

}
