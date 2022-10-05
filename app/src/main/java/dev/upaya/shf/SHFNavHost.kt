package dev.upaya.shf

import androidx.compose.runtime.Composable
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

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {
        composable(route = "exercises") {
            ExerciseList { cfg ->
                viewModel.lastLabel = ""  // Workaround: Avoid showing previous label on new screen
                viewModel.activateLabelMap(cfg.labelMap)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionContent(label = viewModel.lastLabel)
        }
    }

}
