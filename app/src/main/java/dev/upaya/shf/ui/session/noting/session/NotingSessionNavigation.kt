package dev.upaya.shf.ui.session.noting.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraphWithArg


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingScreen(
    navController: NavController,
    onStopButtonClick: () -> Unit,
) {

    composable(routeNotingSession) { backStackEntry ->

        val sessionScope = remember(backStackEntry) { navController.getBackStackEntry(
            routeNotingGraphWithArg
        ) }
        val sessionViewModel: SessionViewModel = hiltViewModel(viewModelStoreOwner = sessionScope)

        val label: Label by sessionViewModel.labelFlow.collectAsState(initial = Label(""))
        val inputEvent by sessionViewModel.inputEventFlow.collectAsState(initial = null)

        DisposableEffect(sessionViewModel) {
            sessionViewModel.startStatsCollection()
            onDispose {
                sessionViewModel.stopStatsCollection()
            }
        }

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
        )

    }

}
