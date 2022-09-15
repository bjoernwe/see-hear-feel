package dev.upaya.shf

import android.view.KeyEvent


class KeyToSHFMap {

    companion object {

        fun getSHF(keyCode: Int): String? {
            return when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> "SEE"
                KeyEvent.KEYCODE_VOLUME_DOWN -> "HEAR"
                KeyEvent.KEYCODE_HEADSETHOOK -> "FEEL"
                else -> null
            }

        }

    }

}
