package dev.upaya.shf.ui.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.Label


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingSessionScreen(
    navController: NavController,
    onStopButtonClick: () -> Unit,
    startUserInteractionForSession: () -> Unit = {},
    stopUserInteractionForSession: () -> Unit = {},
) {

    composable(routeNotingSession) { backStackEntry ->

        val sessionViewModel: SessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraph,
            backStackEntry = backStackEntry,
            navController = navController
        )

        val label: Label by sessionViewModel.labelFlow.collectAsState(initial = Label(""))
        val inputEvent by sessionViewModel.inputEventFlow.collectAsState(initial = null)

        DisposableEffect(sessionViewModel) {

            // session starts
            sessionViewModel.startSession()
            startUserInteractionForSession()

            // session ends
            onDispose {
                stopUserInteractionForSession()
                sessionViewModel.stopSession()
            }
        }

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
        )

    }

}
