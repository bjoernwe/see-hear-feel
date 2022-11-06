package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.inputs.InputEventSource
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject lateinit var inputEventSource: InputEventSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFApp() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            inputEventSource.updateInputEvent(keyCode)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Timber.tag("foo").i("Key released: %s", KeyEvent.keyCodeToString(keyCode))
        return super.onKeyUp(keyCode, event)
    }

    fun setKeepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun clearKeepScreenOn() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

}


@Composable
fun SHFApp() {
    val navController = rememberNavController()
    SHFTheme(darkTheme = true) {
        SHFNavHost(navController = navController)
    }
}
