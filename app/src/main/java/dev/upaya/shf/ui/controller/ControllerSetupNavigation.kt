package dev.upaya.shf.ui.controller

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.KeyPressStateViewModel
import dev.upaya.shf.ui.KeyPressViewModel


private const val routeControllerSetup = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(routeControllerSetup)
}


fun NavGraphBuilder.controllerSetupScreen(
    navController: NavController,
) {

    composable(route = routeControllerSetup) {

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
