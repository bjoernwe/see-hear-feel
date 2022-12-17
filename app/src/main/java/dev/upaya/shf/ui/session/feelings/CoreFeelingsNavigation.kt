package dev.upaya.shf.ui.session.feelings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeFeelingsSession = "feelings_session"


fun NavGraphBuilder.coreFeelingsScreen() {

    composable(
        route = routeFeelingsSession,
    ) {
        CoreFeelingScreen()
    }

}


fun NavController.navigateToFeelingsSession() {
    navigate(routeFeelingsSession)
}
