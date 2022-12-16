package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.controller.ControllerSetupScreen
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.session.noting.NotingScreen
import dev.upaya.shf.ui.session.feelings.CoreFeelingScreen
import dev.upaya.shf.ui.stats.StatsScreen


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
            val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
            ExerciseListScreen(
                exercises = exerciseListViewModel.getExercises(),
                onExerciseClick = { exerciseID ->
                    exerciseListViewModel.getExerciseConfig(exerciseID)?.route?.let { cfg ->
                        sessionViewModel.startSession(exerciseID = exerciseID)
                        navController.navigate(cfg.name)
                    }
                },
                onControllerButtonClick = {
                    navController.navigate("controller")
                }
            )
        }

        composable(route = ExerciseRoute.NOTING.name) {
            NotingScreen(
                onSessionEnd = {
                    sessionViewModel.stopSession()
                },
                onStopButtonClick = {
                    if (sessionViewModel.getNumEvents() == 0) {
                        navController.navigateUp()
                    } else {
                        navController.popBackStack()
                        navController.navigate("stats")
                    }
                },
            )
        }

        composable(route = "stats") {
            StatsScreen()
        }

        composable(route = ExerciseRoute.FEELINGS.name) {
            CoreFeelingScreen(
                onSessionEnd = {
                    sessionViewModel.stopSession()
                }
            )
        }

        composable(route = "controller") {
            ControllerSetupScreen()
        }

    }

}
