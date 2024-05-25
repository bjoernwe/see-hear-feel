package dev.upaya.shf.ui.session.stats

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.routeNotingSession
import dev.upaya.shf.ui.start.routeStartScreen


private const val ROUTE_NOTING_STATS = "noting_stats"


internal fun NavGraphBuilder.notingStatsScreen(
    navController: NavController,
) {

    composable(route = ROUTE_NOTING_STATS) {

        val sessionStatsViewModel: SessionStatsViewModel = hiltViewModel()
        val labelFrequencies by sessionStatsViewModel.labelFrequencies.collectAsState(null)
        val sessionStats by sessionStatsViewModel.sessionStats.collectAsState(null)
        val allTimeStats by sessionStatsViewModel.allTimeStats.collectAsState(null)
        val accumulatedNotingsPerDay by sessionStatsViewModel.accumulatedNotingsPerDay.collectAsState(listOf())

        StatsScreen(
            labelFrequencies = labelFrequencies,
            sessionStats = sessionStats,
            allTimeStats = allTimeStats,
            accumulatedNotingsPerDay = accumulatedNotingsPerDay,
            onBackButtonClick = { navController.popBackStack(route = routeStartScreen, inclusive = false) },
        )

    }

}


internal fun NavController.navigateToNotingStats() {
    this.navigate(route = ROUTE_NOTING_STATS) {
        // Remove session screen from stack. Otherwise back button would lead back to the session.
        popUpTo(route = routeNotingSession) {
            inclusive = true
        }
    }
}
