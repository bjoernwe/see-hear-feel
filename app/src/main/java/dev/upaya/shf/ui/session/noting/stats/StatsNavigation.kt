package dev.upaya.shf.ui.session.noting.stats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeNotingStats = "noting-stats"


internal fun NavGraphBuilder.notingStats() {
    composable(route = routeNotingStats) {
        StatsScreen()
    }
}


internal fun NavController.navigateToNotingStats() {
    this.navigate(route = routeNotingStats)
}
