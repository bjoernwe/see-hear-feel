package dev.upaya.shf.keymaps

import android.view.KeyEvent


class HeadsetKeyMap {

    companion object : ISHFKeyMap  {

        override fun getSHF(keyCode: Int): String? {
            return when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> "SEE"
                KeyEvent.KEYCODE_VOLUME_DOWN -> "HEAR"
                KeyEvent.KEYCODE_HEADSETHOOK -> "FEEL"
                else -> null
            }

        }

    }

}
