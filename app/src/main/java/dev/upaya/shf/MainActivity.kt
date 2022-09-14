package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

import dev.upaya.shf.ui.MainContent


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainContent() }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        val keyCodeStr: String = KeyEvent.keyCodeToString(keyCode)
        Timber.tag("foo").i("Key pressed: %s", keyCodeStr)
        ViewModelProvider(this)[SHFViewModel::class.java].setKey(keyCodeStr)
        return super.onKeyUp(keyCode, event)
    }

}
