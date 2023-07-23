package dev.upaya.shf.ui.controller

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.inputs.input_keys.GlobalInputRegistrarViewModel
import dev.upaya.shf.inputs.key_press_states.KeyPressStateViewModel


private const val routeControllerSetup = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(routeControllerSetup)
}


fun NavGraphBuilder.controllerSetupScreen() {

    composable(route = routeControllerSetup) {

        val globalInputRegistrarViewModel: GlobalInputRegistrarViewModel = hiltViewModel()
        val keyPressStateViewModel: KeyPressStateViewModel = hiltViewModel()
        val keyPressStates by keyPressStateViewModel.keyPressStates.collectAsState()

        DisposableEffect(globalInputRegistrarViewModel) {
            globalInputRegistrarViewModel.switchOn()
            onDispose { globalInputRegistrarViewModel.switchOff() }
        }

        ControllerSetupScreen(
            keyPressStates = keyPressStates,
        )

    }

}
