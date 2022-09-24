package dev.upaya.shf.keymaps

import android.view.KeyEvent.*


class HeadsetKeyMap {

    companion object : IInputKeyMap  {

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
