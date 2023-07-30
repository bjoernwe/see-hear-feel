package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val routeSettings = "settings"


fun NavController.navigateToSettings() {
    this.navigate(routeSettings)
}


fun NavGraphBuilder.settingsScreen() {

    composable(route = routeSettings) {

        val permissionViewModel: PermissionViewModel = hiltViewModel()
        val hasNotificationPermission by permissionViewModel.hasNotificationPermission.collectAsState()
        val hasAccessibilityPermission by permissionViewModel.hasAccessibilityPermission.collectAsState()

        SettingsScreen(
            hasNotificationPermission = hasNotificationPermission,
            hasAccessibilityPermission = hasAccessibilityPermission,
        )
    }

}
