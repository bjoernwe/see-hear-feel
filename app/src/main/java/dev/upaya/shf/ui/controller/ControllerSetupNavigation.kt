package dev.upaya.shf.ui.controller

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.inputs.input_keys.GlobalInputRegistrarViewModel


private const val routeControllerSetup = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(routeControllerSetup)
}


fun NavGraphBuilder.controllerSetupScreen() {

    composable(route = routeControllerSetup) {

        val controllerSetupViewModel: ControllerSetupViewModel = hiltViewModel()
        val keyPressStates by controllerSetupViewModel.keyPressStates.collectAsState()

        val globalInputRegistrarViewModel: GlobalInputRegistrarViewModel = hiltViewModel()

        DisposableEffect(globalInputRegistrarViewModel) {
            globalInputRegistrarViewModel.switchOn()
            onDispose { globalInputRegistrarViewModel.switchOff() }
        }

        ControllerSetupScreen(
            keyPressStates = keyPressStates,
        )

    }

}
