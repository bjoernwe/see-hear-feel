package dev.upaya.shf.activities

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import dev.upaya.shf.SHFViewModel
import dev.upaya.shf.keymaps.HeadsetKeyMap
import dev.upaya.shf.ui.SessionContent
import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber


class SessionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFTheme(darkTheme = true, content = { SessionContent() }) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        HeadsetKeyMap.getInputKey(keyCode)?.let {
            ViewModelProvider(this)[SHFViewModel::class.java].setInputKey(it)
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

}
