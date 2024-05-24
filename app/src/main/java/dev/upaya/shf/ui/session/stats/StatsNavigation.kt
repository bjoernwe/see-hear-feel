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
        val sessionStats by sessionStatsViewModel.sessionStats.collectAsState(null)
        val allTimeStats by sessionStatsViewModel.allTimeStats.collectAsState(null)
        val accumulatedNotingsPerDay by sessionStatsViewModel.accumulatedNotingsPerDay.collectAsState(listOf())
        val amountMindWandering by sessionStatsViewModel.amountMindWandering.collectAsState(0f)

        val numEvents by sessionStatsViewModel.numEvents.collectAsState(0)
        val sessionDurationSeconds by sessionStatsViewModel.sessionDurationSeconds.collectAsState(null)

        StatsScreen(
            numEvents = numEvents,
            sessionDurationSeconds = sessionDurationSeconds,
            sessionStats = sessionStats,
            allTimeStats = allTimeStats,
            accumulatedNotingsPerDay = accumulatedNotingsPerDay,
            amountMindWandering = amountMindWandering,
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
