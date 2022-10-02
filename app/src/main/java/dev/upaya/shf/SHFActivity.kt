package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dev.upaya.shf.exercises.ExerciseConfig
import dev.upaya.shf.exercises.exampleExercises
import dev.upaya.shf.keymaps.HeadsetKeyMap

import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber


class ExerciseListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFApp(exampleExercises) }
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


@Composable
fun SHFApp(exerciseConfigs: List<ExerciseConfig>) {

    val navController = rememberNavController()
    val vm: SHFViewModel = viewModel()

    SHFTheme(darkTheme = true) {
        SHFNavHost(
            navController = navController,
            exerciseConfigs = exerciseConfigs,
            lastInputKey = vm.lastInputKey,
            labelMap = vm.activeLabelMap,
            onSelectExercise = { cfg ->
                vm.activateLabelMap(cfg.labelMap)
            }
        )
    }

}
