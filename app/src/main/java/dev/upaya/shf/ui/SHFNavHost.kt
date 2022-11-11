package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.input.LabelViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.controller.ControllerSetupScreen
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.feelings.CoreFeelingScreen
import dev.upaya.shf.ui.session.SessionScreen
import dev.upaya.shf.ui.stats.StatsScreen


@Composable
fun SHFNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val labelViewModel: LabelViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {

        composable(route = "exercises") {
            val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
            val exercises by exerciseListViewModel.exercises.collectAsState()
            ExerciseListScreen(
                exercises = exercises,
                onExerciseClick = { cfg ->
                    labelViewModel.setCurrentExercise(exerciseConfig = cfg)
                    navController.navigate(cfg.route.name)
                },
                onControllerButtonClick = {
                    navController.navigate("controller")
                }
            )
        }

        composable(route = ExerciseRoute.NOTING.name) {
            SessionScreen(
                labelViewModel = labelViewModel,
                statsButtonOnClick = {
                    navController.popBackStack()
                    navController.navigate("stats")
                }
            )
        }

        composable(route = "stats") {
            StatsScreen(
                labelFreqs = labelViewModel.labelStats
            )
        }

        composable(route = ExerciseRoute.FEELINGS.name) {
            CoreFeelingScreen(labelViewModel = labelViewModel)
        }

        composable(route = "controller") {
            ControllerSetupScreen()
        }

    }

}
