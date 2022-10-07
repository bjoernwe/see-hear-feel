package dev.upaya.shf

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.ExerciseList
import dev.upaya.shf.ui.SessionContent


@Composable
fun SHFNavHost(
    navController: NavHostController,
    viewModel: SHFViewModel,
    modifier: Modifier = Modifier,
) {
    val inputEvent by viewModel.inputEvent.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {
        composable(route = "exercises") {
            ExerciseList { cfg ->
                viewModel.resetInputEvent()  // Workaround: Avoid showing previous label on new screen
                viewModel.activateLabelMap(cfg.labelMap)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionContent(inputEvent = inputEvent)
        }
    }

}
