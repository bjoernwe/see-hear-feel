package dev.upaya.shf.ui.session.noting.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.session.noting.NotingScreen
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.getScopedSessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraphWithArg


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingSessionScreen(
    navController: NavController,
    onStopButtonClick: () -> Unit,
    onSessionStart: () -> Unit = {},
    onSessionStop: () -> Unit = {},
    onToggleBackgroundSession: (Boolean) -> Unit,
) {

    composable(routeNotingSession) { backStackEntry ->

        val sessionViewModel: SessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraphWithArg,
            backStackEntry = backStackEntry,
            navController = navController
        )

        val label: Label by sessionViewModel.labelFlow.collectAsState(initial = Label(""))
        val inputEvent by sessionViewModel.inputEventFlow.collectAsState(initial = null)
        val usingBackgroundKeySource by sessionViewModel.usingBackgroundSource.collectAsState()

        DisposableEffect(sessionViewModel) {
            onSessionStart()
            sessionViewModel.startStatsCollection()
            onDispose {
                sessionViewModel.stopStatsCollection()
                onSessionStop()
            }
        }

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
            usingBackgroundKeySource = usingBackgroundKeySource,
            onToggleBackgroundSession = onToggleBackgroundSession,
        )

    }

}
