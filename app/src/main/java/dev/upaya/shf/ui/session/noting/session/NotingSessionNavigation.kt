package dev.upaya.shf.ui.session.noting.session

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_keys.GlobalInputRegistrarViewModel
import dev.upaya.shf.ui.session.noting.NotingScreen
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.getScopedSessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraphWithArg


internal const val routeNotingSession = "noting_session"


internal fun NavGraphBuilder.notingSessionScreen(
    navController: NavController,
    onStopButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    onSessionStart: () -> Unit = {},
    onSessionStop: () -> Unit = {},
) {

    composable(routeNotingSession) { backStackEntry ->

        val globalInputRegistrarViewModel: GlobalInputRegistrarViewModel = hiltViewModel()
        val sessionViewModel: SessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraphWithArg,
            backStackEntry = backStackEntry,
            navController = navController
        )

        val label: Label by sessionViewModel.labelFlow.collectAsState(initial = Label(""))
        val inputEvent by sessionViewModel.inputEventFlow.collectAsState(initial = null)

        DisposableEffect(sessionViewModel) {

            // session starts
            onSessionStart()
            sessionViewModel.startStatsCollection()
            globalInputRegistrarViewModel.switchOn()

            onDispose {
                // session ends
                globalInputRegistrarViewModel.switchOff()
                sessionViewModel.stopStatsCollection()
                onSessionStop()
            }
        }

        NotingScreen(
            label = label,
            inputEvent = inputEvent,
            onStopButtonClick = onStopButtonClick,
            onSettingsButtonClick = onSettingsButtonClick,
        )

    }

}
