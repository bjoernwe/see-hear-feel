package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch


private const val routeSettings = "settings"


fun NavController.navigateToSettings() {
    this.navigate(routeSettings)
}


fun NavGraphBuilder.settingsScreen(
    showAccessibilitySettings: () -> Unit,
) {

    composable(route = routeSettings) {

        val permissionViewModel: PermissionViewModel = hiltViewModel()
        val hasAccessibilityPermission by permissionViewModel.hasAccessibilityPermission.collectAsState()

        val preferenceViewModel: PreferenceViewModel = hiltViewModel()
        val isLockScreenSessionEnabled by preferenceViewModel.isLockScreenSessionEnabled.collectAsState(false)

        val scope = rememberCoroutineScope()

        SettingsScreen(
            hasAccessibilityPermission = hasAccessibilityPermission,
            isLockScreenSessionEnabled = isLockScreenSessionEnabled,
            onSwitchLockScreenSession = { newSwitchValue ->
                scope.launch {
                    if (!hasAccessibilityPermission)
                        showAccessibilitySettings()
                    else
                        preferenceViewModel.setLockScreenPreference(newSwitchValue)
                }
            },
        )
    }

}
