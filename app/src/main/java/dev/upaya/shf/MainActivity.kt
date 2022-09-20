package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

import dev.upaya.shf.ui.SessionContent
import dev.upaya.shf.ui.theme.SHFTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFTheme(darkTheme = true, content = { SessionContent() }) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        val shfEvent = KeyToSHFMap.getSHF(keyCode)
        ViewModelProvider(this)[SHFViewModel::class.java].setSHFEvent(shfEvent)

        return if (shfEvent != null) true else super.onKeyDown(keyCode, event)
    }

}
