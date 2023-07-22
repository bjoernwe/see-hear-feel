package dev.upaya.shf.ui.session.noting

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.ui.session.noting.session.notingSessionScreen
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStatsScreen


private const val routeNotingGraph = "noting_graph"
private const val routeNotingSession = "noting_session"

internal const val routeArgExerciseId = "exerciseId"
internal const val routeNotingGraphWithArg = "$routeNotingGraph/{$routeArgExerciseId}"


fun NavController.navigateToNoting(exerciseId: ExerciseId) {
    navigate("${routeNotingGraph}/${exerciseId}")
}


fun NavGraphBuilder.notingGraph(
    navController: NavController,
    onSessionStart: () -> Unit = {},
    onSessionStop: () -> Unit = {},
) {
    
    navigation(
        route = routeNotingGraphWithArg,
        startDestination = routeNotingSession,
    ) {

        notingSessionScreen(
            navController = navController,
            onStopButtonClick = {
                navController.navigateToNotingStats()
            },
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
        )

        notingStatsScreen(
            navController = navController,
        )

    }
    
}


@Composable
internal fun getScopedSessionViewModel(
    routeForScope: String,
    backStackEntry: NavBackStackEntry,
    navController: NavController,
): SessionViewModel {

    val sessionScope = remember(backStackEntry) {
        navController.getBackStackEntry(routeForScope)
    }

    return hiltViewModel(viewModelStoreOwner = sessionScope)
}
