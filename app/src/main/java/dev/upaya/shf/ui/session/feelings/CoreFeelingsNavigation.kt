package dev.upaya.shf.ui.session.feelings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseID


private const val routeFeelingsSession = "feelings_session"
internal const val routeArgExerciseId = "exerciseId"
internal const val routeFeelingsWithArg = "$routeFeelingsSession/{${routeArgExerciseId}}"


fun NavGraphBuilder.coreFeelingsScreen() {

    composable(
        route = routeFeelingsWithArg,
    ) {
        CoreFeelingScreen()
    }

}


fun NavController.navigateToFeelingsSession(exerciseId: ExerciseID) {
    navigate("${routeFeelingsSession}/${exerciseId}")
}
