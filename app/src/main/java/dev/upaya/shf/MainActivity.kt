package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

import dev.upaya.shf.ui.MainContent
import dev.upaya.shf.ui.theme.SHFTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFTheme(darkTheme = true, content = { MainContent() }) }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        val keyEvent = KeyToSHFMap.getSHF(keyCode)
        ViewModelProvider(this)[SHFViewModel::class.java].setSHFEvent(keyEvent)

        // This is intended to intercept handled events so that they are not passed on to the
        // system. For instance, we don't want to change the phone's volume every time a key is
        // pressed. Interception doesn't work, though!
        return if (keyEvent != null) true else super.onKeyUp(keyCode, event)
    }

}
