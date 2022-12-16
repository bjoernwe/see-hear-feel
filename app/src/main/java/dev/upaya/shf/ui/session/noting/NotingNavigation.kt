package dev.upaya.shf.ui.session.noting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute


fun NavGraphBuilder.notingScreen(
    onSessionEnd: () -> Unit,
    onStopButtonClick: () -> Unit,
) {

    composable(ExerciseRoute.NOTING.name) {

        NotingScreen(
            onSessionEnd = onSessionEnd,
            onStopButtonClick = onStopButtonClick,
        )

    }

}
