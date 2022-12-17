package dev.upaya.shf.ui.session.noting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStats


private const val routeNotingGraph = "noting_graph"
private const val routeNotingSession = "noting_session"

private const val routeArgExerciseId = "exerciseId"


fun NavGraphBuilder.notingGraph(
    navController: NavController,
    sessionViewModel: SessionViewModel,
) {
    
    navigation(
        route = "${routeNotingGraph}/{${routeArgExerciseId}}",
        startDestination = routeNotingSession,
    ) {

        notingScreen(
            onSessionEnd = {
                sessionViewModel.stopSession()
            },
            onStopButtonClick = {
                navController.popBackStack()
                navController.navigateToNotingStats()
            },
        )

        notingStats()

    }
    
}


private fun NavGraphBuilder.notingScreen(
    onSessionEnd: () -> Unit,
    onStopButtonClick: () -> Unit,
) {

    composable(routeNotingSession) {

        NotingScreen(
            onSessionEnd = onSessionEnd,
            onStopButtonClick = onStopButtonClick,
        )

    }

}


fun NavController.navigateToNoting(exerciseID: ExerciseID) {
    navigate("${routeNotingGraph}/${exerciseID}")
}
