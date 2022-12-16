package dev.upaya.shf.ui.session.noting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStats


fun NavGraphBuilder.notingGraph(
    navController: NavController,
    sessionViewModel: SessionViewModel,
) {
    
    navigation(startDestination = "noting_session", route = ExerciseRoute.NOTING.name) {

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

    composable("noting_session") {

        NotingScreen(
            onSessionEnd = onSessionEnd,
            onStopButtonClick = onStopButtonClick,
        )

    }

}
