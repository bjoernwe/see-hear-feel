package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber


class ExerciseListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFApp() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        val vm: SHFViewModel = ViewModelProvider(this)[SHFViewModel::class.java]
        val inputKey = vm.inputDevice.getInputKey(keyCode) ?: return super.onKeyDown(keyCode, event)

        vm.setInputKey(inputKey)

        return true
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
