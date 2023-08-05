package dev.upaya.shf.inputs.keys

import android.view.KeyEvent.*


class InputKeyMapping {

    companion object  {

        fun getInputKey(keyCode: Int): InputKey {
            return when (keyCode) {
                KEYCODE_DPAD_UP -> InputKey.KEY_UP
                KEYCODE_DPAD_DOWN -> InputKey.KEY_DOWN
                KEYCODE_DPAD_LEFT -> InputKey.KEY_LEFT
                KEYCODE_DPAD_RIGHT -> InputKey.KEY_RIGHT
                KEYCODE_BUTTON_L1 -> InputKey.KEY_L1
                KEYCODE_BUTTON_L2 -> InputKey.KEY_L2
                KEYCODE_BUTTON_A -> InputKey.KEY_A
                KEYCODE_BUTTON_B -> InputKey.KEY_B
                KEYCODE_BUTTON_X -> InputKey.KEY_X
                KEYCODE_BUTTON_Y -> InputKey.KEY_Y
                KEYCODE_BUTTON_R1 -> InputKey.KEY_R1
                KEYCODE_BUTTON_R2 -> InputKey.KEY_R2
                KEYCODE_VOLUME_UP -> InputKey.KEY_RIGHT
                KEYCODE_VOLUME_DOWN -> InputKey.KEY_LEFT
                KEYCODE_HEADSETHOOK -> InputKey.KEY_DOWN
                else -> InputKey.UNMAPPED
            }
        }

    }

}
