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

        val settingsViewModel: SettingsViewModel = hiltViewModel()
        val hasNotificationPermission by settingsViewModel.hasNotificationPermission.collectAsState()
        val hasAccessibilityPermission by settingsViewModel.hasAccessibilityPermission.collectAsState()
        val backgroundSessionPermitted by settingsViewModel.backgroundSessionPermitted.collectAsState()

        SettingsScreen(
            hasNotificationPermission = hasNotificationPermission,
            hasAccessibilityPermission = hasAccessibilityPermission,
            backgroundSessionPermitted = backgroundSessionPermitted,
        )
    }

}
