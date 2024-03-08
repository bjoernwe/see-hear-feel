package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.settings.controller.navigateToControllerSetup


private const val ROUTE_SETTINGS = "settings"


fun NavController.navigateToSettings() {
    this.navigate(ROUTE_SETTINGS)
}


fun NavGraphBuilder.settingsScreen(
    navController: NavController,
) {

    composable(route = ROUTE_SETTINGS) {

        val preferenceViewModel: PreferenceViewModel = hiltViewModel()
        val isPacingEnabled by preferenceViewModel.isPacingEnabled.collectAsState(initial = false)

        SettingsScreen(
            onBackButtonClick = navController::popBackStack,
            onControllerSetupEntryClick = navController::navigateToControllerSetup,
            isPacingEnabled = isPacingEnabled,
            onSwitchPacing = preferenceViewModel::setPacingPreference,
        )
    }

}
