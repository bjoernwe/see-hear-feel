package dev.upaya.shf.ui.controller

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeControllerSetup = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(routeControllerSetup)
}


fun NavGraphBuilder.controllerSetupScreen() {

    composable(route = routeControllerSetup) {

        val controllerSetupViewModel: ControllerSetupViewModel = hiltViewModel()
        val keyPressStates by controllerSetupViewModel.keyPressStates.collectAsState()

        ControllerSetupScreen(
            keyPressStates = keyPressStates,
        )

    }

}
