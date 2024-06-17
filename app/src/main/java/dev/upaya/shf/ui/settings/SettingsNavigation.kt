package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.settings.controller.navigateToControllerSetup
import dev.upaya.shf.ui.start.AuthViewModel


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
        val isLogInEnabled by preferenceViewModel.isLogInEnabled.collectAsState(initial = false)

        val context = LocalContext.current
        val authViewModel: AuthViewModel = hiltViewModel()
        val userEmailAddress by authViewModel.userEmail.collectAsState()

        SettingsScreen(
            onBackButtonClick = navController::popBackStack,
            onLogInClick = { authViewModel.signIn(context) },
            onControllerSetupEntryClick = navController::navigateToControllerSetup,
            userEmailAddress = userEmailAddress,
            toggleLogInEnabled = isLogInEnabled,
            isPacingEnabled = isPacingEnabled,
            onSwitchPacing = preferenceViewModel::setPacingPreference,
        )
    }

}
