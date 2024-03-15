package dev.upaya.shf.ui.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.data.gamepad_input.SHFLabelEvent


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingSessionScreen(
    navController: NavController,
    navigateToNotingStats: () -> Unit,
) {

    composable(routeNotingSession) {

        val sessionViewModel: SessionViewModel = hiltViewModel()

        val label: SHFLabelEvent? by sessionViewModel.labelFlow.collectAsState(initial = null)
        val numInputEvents by sessionViewModel.numEvents.collectAsState()

        // session starts
        LaunchedEffect(sessionViewModel) {
            sessionViewModel.onSessionStart()
        }

        // session ends
        DisposableEffect(sessionViewModel) {
            onDispose {
                sessionViewModel.onSessionStop()
            }
        }

        val onStopButtonClick: () -> Unit = {
            if (numInputEvents > 0)
                navigateToNotingStats()
            else
                navController.popBackStack()
        }

        NotingScreen(
            labelEvent = label,
            onStopButtonClick = onStopButtonClick,
            numInputEvents = numInputEvents,
        )
    }
}
