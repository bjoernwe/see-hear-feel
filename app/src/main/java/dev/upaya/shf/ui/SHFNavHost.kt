package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.ExerciseViewModel
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.session.SessionScreen


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
            val exerciseViewModel: ExerciseViewModel = hiltViewModel()
            ExerciseListScreen(
                viewModel = exerciseViewModel,
            ) { cfg ->
                sessionViewModel.setCurrentExercise(exerciseConfig = cfg)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionScreen(viewModel = sessionViewModel)
        }
    }

}
