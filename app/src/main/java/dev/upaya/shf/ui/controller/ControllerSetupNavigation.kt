package dev.upaya.shf.ui.controller

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeControllerSetup = "controller"


fun NavController.navigateToControllerSetup() {
    this.navigate(routeControllerSetup)
}


fun NavGraphBuilder.controllerSetupScreen() {
    composable(route = routeControllerSetup) {
        ControllerSetupScreen()
    }

}
