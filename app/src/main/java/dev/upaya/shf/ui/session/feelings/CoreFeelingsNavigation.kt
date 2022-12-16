package dev.upaya.shf.ui.session.feelings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.session.SessionViewModel


fun NavGraphBuilder.coreFeelingsScreen(
    sessionViewModel: SessionViewModel,
) {

    composable(route = ExerciseRoute.FEELINGS.name) {
        CoreFeelingScreen(
            onSessionEnd = {
                sessionViewModel.stopSession()
            }
        )
    }

}
