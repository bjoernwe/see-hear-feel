package dev.upaya.shf.ui.session.feelings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.SessionViewModel


private const val routeFeelingsSession = "feelings_session"


fun NavGraphBuilder.coreFeelingsScreen(
    sessionViewModel: SessionViewModel,
) {

    composable(
        route = routeFeelingsSession,
    ) {
        CoreFeelingScreen(
            onSessionEnd = {
                sessionViewModel.stopSession()
            }
        )
    }

}


fun NavController.navigateToFeelingsSession() {
    navigate(routeFeelingsSession)
}
