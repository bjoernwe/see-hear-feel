package dev.upaya.shf.ui.session.stats

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.session.routeNotingGraph


private const val ROUTE_NOTING_STATS = "noting_stats"


internal fun NavGraphBuilder.notingStatsScreen(
    navController: NavController,
) {

    composable(route = ROUTE_NOTING_STATS) { backStackEntry ->

        val sessionScope = remember(backStackEntry) {
            navController.getBackStackEntry(routeNotingGraph)
        }
        val sessionViewModel: SessionViewModel = hiltViewModel(viewModelStoreOwner = sessionScope)

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
