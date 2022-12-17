package dev.upaya.shf.ui.session.noting

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.session.input.InputViewModel
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStatsScreen


private const val routeNotingGraph = "noting_graph"
private const val routeNotingSession = "noting_session"

internal const val routeArgExerciseId = "exerciseId"
internal const val routeNotingGraphWithArg = "${routeNotingGraph}/{${routeArgExerciseId}}"


fun NavGraphBuilder.notingGraph(
    navController: NavController,
) {
    
    navigation(
        route = routeNotingGraphWithArg,
        startDestination = routeNotingSession,
    ) {

        notingScreen(
            navController = navController,
            onStopButtonClick = {
                navController.navigateToNotingStats()
            },
        )

        notingStatsScreen(
            navController = navController,
        )

    }
    
}


private fun NavGraphBuilder.notingScreen(
    navController: NavController,
    onStopButtonClick: () -> Unit,
) {

    composable(routeNotingSession) { backStackEntry ->

        val sessionScope = remember(backStackEntry) { navController.getBackStackEntry(routeNotingGraphWithArg) }
        val sessionViewModel: SessionViewModel = hiltViewModel(viewModelStoreOwner = sessionScope)
        val inputViewModel: InputViewModel = hiltViewModel()

        val label: Label by sessionViewModel.label.collectAsState(initial = Label(""))
        val inputEvent by inputViewModel.inputEvent.collectAsState()

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
        )

    }

}


fun NavController.navigateToNoting(exerciseID: ExerciseID) {
    navigate("${routeNotingGraph}/${exerciseID}")
}
