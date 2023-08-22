package dev.upaya.shf.ui.session.stats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.getScopedSessionViewModel
import dev.upaya.shf.ui.session.routeNotingGraph


private const val ROUTE_NOTING_STATS = "noting_stats"


internal fun NavGraphBuilder.notingStatsScreen(
    navController: NavController,
) {

    composable(route = ROUTE_NOTING_STATS) { backStackEntry ->

        val sessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraph,
            backStackEntry = backStackEntry,
            navController = navController,
        )

        StatsScreen(
            sessionLength = sessionViewModel.getSessionLength(),
            numEvents = sessionViewModel.getNumEvents(),
            labelFreqs = sessionViewModel.getLabelFreqs(),
            onBackButtonClick = navController::popBackStack,
        )

    }

}


internal fun NavController.navigateToNotingStats() {
    this.navigate(route = ROUTE_NOTING_STATS)
}
