package dev.upaya.shf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.ExerciseConfig
import dev.upaya.shf.input_devices.InputKey
import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF
import dev.upaya.shf.ui.ExerciseList
import dev.upaya.shf.ui.SessionContent


@Composable
fun SHFNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    exerciseConfigs: List<ExerciseConfig>,
    lastInputKey: InputKey?,
    labelMap: LabelMap = LabelMapSHF,
    onSelectExercise: (ExerciseConfig) -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {
        composable(route = "exercises") {
            ExerciseList(exerciseConfigs = exerciseConfigs) { cfg ->
                onSelectExercise(cfg)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionContent(lastInputKey = lastInputKey, labelMap = labelMap)
        }
    }
}
