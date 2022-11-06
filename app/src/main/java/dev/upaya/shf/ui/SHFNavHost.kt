package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.exercises.SessionViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.exercises.ExerciseListScreen
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.feelings.CoreFeelingScreen
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
            val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
            val exercises by exerciseListViewModel.exercises.collectAsState()
            ExerciseListScreen(exercises = exercises) { cfg ->
                sessionViewModel.setCurrentExercise(exerciseConfig = cfg)
                navController.navigate(cfg.route.name)
            }
        }

        composable(route = ExerciseRoute.NOTING.name) {
            SessionScreen(sessionViewModel = sessionViewModel)
        }

        composable(route = ExerciseRoute.FEELINGS.name) {
            CoreFeelingScreen(sessionViewModel = sessionViewModel)
        }

    }

}
