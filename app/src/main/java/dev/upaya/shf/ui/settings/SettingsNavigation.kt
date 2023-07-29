package dev.upaya.shf.ui.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeSettings = "settings"


fun NavController.navigateToSettings() {
    this.navigate(routeSettings)
}


fun NavGraphBuilder.settingsScreen() {

    composable(route = routeSettings) {
        SettingsScreen()
    }

}
