package dev.upaya.shf.ui.exercises

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseId


private const val routeExerciseList = "exercises"


fun NavGraphBuilder.exerciseListScreen(
    onExerciseClick: (ExerciseId) -> Unit,
    onControllerButtonClick: () -> Unit,
) {

    composable(route = routeExerciseList) {

        val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
        val exercises by exerciseListViewModel.exercises.collectAsState()

        ExerciseListScreen(
            exercises = exercises,
            onExerciseClick = onExerciseClick,
            onControllerButtonClick = onControllerButtonClick,
        )

    }

}
