package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.controller.controllerSetupScreen
import dev.upaya.shf.ui.controller.navigateToControllerSetup
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.exercises.navigateToExercise
import dev.upaya.shf.ui.session.feelings.CoreFeelingScreen
import dev.upaya.shf.ui.session.noting.notingGraph


@Composable
fun SHFNavHost(
    navController: NavHostController = rememberNavController(),
) {

    val sessionViewModel: SessionViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "exercises",
    ) {

        composable(route = "exercises") {
            val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
            val exercises by exerciseListViewModel.exercises.collectAsState()
            ExerciseListScreen(
                exercises = exercises,
                onExerciseClick = { exerciseID ->
                    exerciseListViewModel.getExerciseConfig(exerciseID)?.route?.let { exerciseRoute ->
                        sessionViewModel.startSession(exerciseID = exerciseID)
                        navController.navigateToExercise(
                            exerciseRoute = exerciseRoute,
                            exerciseID = exerciseID,
                        )
                    }
                },
                onControllerButtonClick = {
                    navController.navigateToControllerSetup()
                }
            )
        }

        notingGraph(
            navController = navController,
            sessionViewModel = sessionViewModel,
        )

        composable(route = ExerciseRoute.FEELINGS.name) {
            CoreFeelingScreen(
                onSessionEnd = {
                    sessionViewModel.stopSession()
                }
            )
        }

        controllerSetupScreen()

    }

}
