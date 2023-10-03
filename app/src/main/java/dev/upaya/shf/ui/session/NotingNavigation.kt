package dev.upaya.shf.ui.session

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.upaya.shf.ui.session.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.stats.notingStatsScreen


internal const val routeNotingGraph = "noting_graph"


fun NavController.navigateToNoting() {
    navigate(routeNotingSession)
}


fun NavGraphBuilder.notingGraph(
    navController: NavController,
) {
    
    navigation(
        route = routeNotingGraph,
        startDestination = routeNotingSession,
    ) {

        notingSessionScreen(
            navController = navController,
            navigateToNotingStats = navController::navigateToNotingStats,
        )

        notingStatsScreen(
            navController = navController,
        )

    }
    
}
