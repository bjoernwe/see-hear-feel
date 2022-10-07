package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.exercises.ExerciseListScreen
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
            val vm: SessionViewModel = viewModel()
            ExerciseListScreen { cfg ->
                vm.setCurrentExercise(exerciseConfig = cfg)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionScreen()
        }
    }

}
