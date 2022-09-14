package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.upaya.shf.ui.MainContent
import timber.log.Timber


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainContent() }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))
        return super.onKeyUp(keyCode, event)
    }

}
