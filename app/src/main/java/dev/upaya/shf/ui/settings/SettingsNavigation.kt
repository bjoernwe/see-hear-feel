package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.settings.controller.navigateToControllerSetup


private const val routeSettings = "settings"


fun NavController.navigateToSettings() {
    this.navigate(routeSettings)
}


fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    showAccessibilitySettings: () -> Unit,
) {

    composable(route = routeSettings) {

        val permissionViewModel: PermissionViewModel = hiltViewModel()
        val hasAccessibilityPermission by permissionViewModel.hasAccessibilityPermission.collectAsState()

        val preferenceViewModel: PreferenceViewModel = hiltViewModel()
        val isLockScreenPreferred by preferenceViewModel.isLockScreenPreferred.collectAsState(initial = false)
        val isPacingEnabled by preferenceViewModel.isPacingEnabled.collectAsState(initial = false)

        SettingsScreen(
            onBackButtonClick = navController::popBackStack,
            isLockScreenPreferred = isLockScreenPreferred,
            hasAccessibilityPermission = hasAccessibilityPermission,
            onSwitchLockScreenSession = preferenceViewModel::setLockScreenPreference,
            onRequestAccessibilitySettings = showAccessibilitySettings,
            onControllerSetupEntryClick = navController::navigateToControllerSetup,
            isPacingEnabled = isPacingEnabled,
            onSwitchPacing = preferenceViewModel::setPacingPreference,
        )
    }

}
