package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
        if (inputEventSource.updateInputKey(keyCode))
            return true
        return super.onKeyDown(keyCode, event)
    }

}


@Composable
fun SHFApp() {

    val navController = rememberNavController()
    val viewModel: SHFViewModel = viewModel()

    SHFTheme(darkTheme = true) {
        SHFNavHost(
            navController = navController,
            viewModel = viewModel,
        )
    }

}
