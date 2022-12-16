package dev.upaya.shf.ui.stats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeNotingStats = "noting-stats"


fun NavGraphBuilder.notingStats() {
    composable(route = routeNotingStats) {
        StatsScreen()
    }
}


fun NavController.navigateToNotingStats() {
    this.navigate(route = routeNotingStats)
}
