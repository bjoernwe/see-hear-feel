package dev.upaya.shf.ui.session.noting

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.session.SessionViewModel


fun NavGraphBuilder.notingScreen(
    onStopButtonClick: () -> Unit,
) {

    composable(ExerciseRoute.NOTING.name) {

        val sessionViewModel: SessionViewModel = hiltViewModel()

        NotingScreen(
            onSessionEnd = {
                sessionViewModel.stopSession()
            },
            onStopButtonClick = onStopButtonClick,
        )

    }

}
