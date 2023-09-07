package dev.upaya.shf.ui.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.Label


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingSessionScreen(
    navController: NavController,
    navigateToNotingStats: () -> Unit,
    startUserInteractionForSession: (Boolean) -> Unit = {},
    stopUserInteractionForSession: () -> Unit = {},
) {

    composable(routeNotingSession) {

        val sessionViewModel: SessionViewModel = hiltViewModel()

        val label: Label by sessionViewModel.labelFlow.collectAsState(initial = Label(""))
        val inputEvent by sessionViewModel.inputEventFlow.collectAsState(initial = null)

        // session starts
        LaunchedEffect(sessionViewModel) {
            sessionViewModel.startSession(onStartSession = startUserInteractionForSession)
        }

        // session ends
        DisposableEffect(sessionViewModel) {
            onDispose {
                sessionViewModel.stopSession(onStopSession = stopUserInteractionForSession)
            }
        }

        val onStopButtonClick: () -> Unit = {
            if (sessionViewModel.getNumEvents() > 0)
                navigateToNotingStats()
            else
                navController.popBackStack()
        }

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
        )
    }
}
