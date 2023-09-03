package dev.upaya.shf.ui.settings.controller

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.KeyPressStateViewModel
import dev.upaya.shf.ui.KeyPressViewModel


private const val ROUTE_CONTROLLER_SETUP = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(ROUTE_CONTROLLER_SETUP)
}


fun NavGraphBuilder.controllerSetupScreen(
    navController: NavController,
) {

    composable(route = ROUTE_CONTROLLER_SETUP) {

        val keyPressViewModel: KeyPressViewModel = hiltViewModel()
        val keyPressStateViewModel: KeyPressStateViewModel = hiltViewModel()
        val keyPressStates by keyPressStateViewModel.keyPressStates.collectAsState()

        DisposableEffect(keyPressViewModel) {
            keyPressViewModel.enableKeyCapturing()
            onDispose { keyPressViewModel.disableKeyCapturing() }
        }

        ControllerSetupScreen(
            keyPressStates = keyPressStates,
            onBackButtonClick = navController::popBackStack,
        )

    }

}
