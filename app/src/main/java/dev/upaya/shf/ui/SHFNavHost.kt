package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.controller.controllerSetupScreen
import dev.upaya.shf.ui.controller.navigateToControllerSetup
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.exercises.exerciseListScreen
import dev.upaya.shf.ui.session.navigateToExerciseSession
import dev.upaya.shf.ui.session.feelings.coreFeelingsScreen
import dev.upaya.shf.ui.session.noting.notingGraph


@Composable
fun SHFNavHost(
    navController: NavHostController = rememberNavController(),
) {

    val sessionViewModel: SessionViewModel = hiltViewModel()
    val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "exercises",
    ) {

        exerciseListScreen(
            onExerciseClick = { exerciseID ->
                exerciseListViewModel.getExerciseConfig(exerciseID)?.route?.let { exerciseRoute ->
                    sessionViewModel.startSession(exerciseID = exerciseID)
                    navController.navigateToExerciseSession(
                        exerciseRoute = exerciseRoute,
                        exerciseID = exerciseID,
                    )
                }
            },
            onControllerButtonClick = {
                navController.navigateToControllerSetup()
            }
        )

        notingGraph(
            navController = navController,
            sessionViewModel = sessionViewModel,
        )

        coreFeelingsScreen(
            sessionViewModel = sessionViewModel,
        )

        controllerSetupScreen()

    }

}
