package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import dev.upaya.shf.ui.feelings.CoreFeelingScreen
import dev.upaya.shf.ui.session.SessionScreen


@Composable
fun SHFNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {

        composable(route = "exercises") {
            val sessionViewModel: SessionViewModel = hiltViewModel()
            val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
            val exercises by exerciseListViewModel.exercises.collectAsState()
            ExerciseListScreen(
                exercises = exercises,
                onExerciseClick = { cfg ->
                    sessionViewModel.beginSession(exerciseConfig = cfg)
                    navController.navigate(cfg.route.name)
                },
                onControllerButtonClick = {
                    navController.navigate("controller")
                }
            )
        }

        composable(route = ExerciseRoute.NOTING.name) {
            SessionScreen()
        }

        composable(route = ExerciseRoute.FEELINGS.name) {
            CoreFeelingScreen()
        }

        composable(route = "controller") {
            ControllerSetupScreen()
        }

    }

}
