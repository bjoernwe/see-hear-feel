package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.feelings.CoreFeelingScreen
import dev.upaya.shf.ui.session.SessionScreen


enum class SHFSessionRoutes {
    NOTING, FEELINGS
}


@Composable
fun SHFNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val sessionViewModel: SessionViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {

        composable(route = "exercises") {
            ExerciseListScreen { cfg ->
                sessionViewModel.setCurrentExercise(exerciseConfig = cfg)
                navController.navigate(cfg.route.name)
            }
        }

        composable(route = SHFSessionRoutes.NOTING.name) {
            SessionScreen(viewModel = sessionViewModel)
        }

        composable(route = SHFSessionRoutes.FEELINGS.name) {
            CoreFeelingScreen(sessionViewModel = sessionViewModel)
        }

    }

}
