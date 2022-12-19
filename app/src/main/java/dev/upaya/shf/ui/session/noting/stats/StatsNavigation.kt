package dev.upaya.shf.ui.session.noting.stats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.getScopedSessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraphWithArg


private const val routeNotingStats = "noting_stats"


internal fun NavGraphBuilder.notingStatsScreen(
    navController: NavController,
) {

    composable(route = routeNotingStats) { backStackEntry ->

        val sessionViewModel: SessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraphWithArg,
            backStackEntry = backStackEntry,
            navController = navController
        )

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
