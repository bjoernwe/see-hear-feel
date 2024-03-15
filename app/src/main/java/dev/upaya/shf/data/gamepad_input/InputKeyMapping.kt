package dev.upaya.shf.data.gamepad_input

import android.view.KeyEvent.*


class InputKeyMapping {

    companion object  {

        fun getInputKey(keyCode: Int, allowUnmapped: Boolean = true): GamepadKey? {
            return when (keyCode) {
                KEYCODE_DPAD_UP -> GamepadKey.KEY_UP
                KEYCODE_DPAD_DOWN -> GamepadKey.KEY_DOWN
                KEYCODE_DPAD_LEFT -> GamepadKey.KEY_LEFT
                KEYCODE_DPAD_RIGHT -> GamepadKey.KEY_RIGHT
                KEYCODE_BUTTON_L1 -> GamepadKey.KEY_L1
                KEYCODE_BUTTON_L2 -> GamepadKey.KEY_L2
                KEYCODE_BUTTON_A -> GamepadKey.KEY_A
                KEYCODE_BUTTON_B -> GamepadKey.KEY_B
                KEYCODE_BUTTON_X -> GamepadKey.KEY_X
                KEYCODE_BUTTON_Y -> GamepadKey.KEY_Y
                KEYCODE_BUTTON_R1 -> GamepadKey.KEY_R1
                KEYCODE_BUTTON_R2 -> GamepadKey.KEY_R2
                KEYCODE_VOLUME_UP -> GamepadKey.KEY_RIGHT
                KEYCODE_VOLUME_DOWN -> GamepadKey.KEY_LEFT
                KEYCODE_HEADSETHOOK -> GamepadKey.KEY_DOWN
                else -> if (allowUnmapped) GamepadKey.UNMAPPED else null
            }
        }

    }

}
