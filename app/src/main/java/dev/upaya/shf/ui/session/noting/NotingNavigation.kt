package dev.upaya.shf.ui.session.noting

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.upaya.shf.ui.session.noting.session.notingSessionScreen
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStatsScreen


internal const val routeNotingGraph = "noting_graph"
private const val routeNotingSession = "noting_session"


fun NavController.navigateToNoting() {
    navigate(routeNotingSession)
}


fun NavGraphBuilder.notingGraph(
    navController: NavController,
    startUserInteractionForSession: () -> Unit = {},
    stopUserInteractionForSession: () -> Unit = {},
) {
    
    navigation(
        route = routeNotingGraph,
        startDestination = routeNotingSession,
    ) {

        notingSessionScreen(
            navController = navController,
            onStopButtonClick = navController::navigateToNotingStats,
            startUserInteractionForSession = startUserInteractionForSession,
            stopUserInteractionForSession = stopUserInteractionForSession,
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
