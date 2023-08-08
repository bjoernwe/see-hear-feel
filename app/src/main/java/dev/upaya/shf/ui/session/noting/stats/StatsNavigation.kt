package dev.upaya.shf.ui.session.noting.stats

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraph


private const val routeNotingStats = "noting_stats"


internal fun NavGraphBuilder.notingStatsScreen(
    navController: NavController,
) {

    composable(route = routeNotingStats) { backStackEntry ->

        val sessionScope = remember(backStackEntry) { navController.getBackStackEntry(routeNotingGraph) }
        val sessionViewModel: SessionViewModel = hiltViewModel(viewModelStoreOwner = sessionScope)

        StatsScreen(
            sessionLength = sessionViewModel.getSessionLength(),
            numEvents = sessionViewModel.getNumEvents(),
            labelFreqs = sessionViewModel.getLabelFreqs(),
        )

    }

}


internal fun NavController.navigateToNotingStats() {
    this.navigate(route = routeNotingStats)
}
