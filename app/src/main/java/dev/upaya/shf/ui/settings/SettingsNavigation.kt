package dev.upaya.shf.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.data.auth.LogInStatus
import dev.upaya.shf.ui.settings.composables.UserSettingParams
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

        val context = LocalContext.current
        val authViewModel: AuthViewModel = hiltViewModel()
        val isLogInEnabled by authViewModel.isLogInEnabled.collectAsState(initial = false)
        val logInStatus by authViewModel.logInStatus.collectAsState(LogInStatus.LOGGED_OUT)
        val userEmailAddress by authViewModel.userEmail.collectAsState()
        val userSettingParams = UserSettingParams(
            logInStatus = logInStatus,
            emailAddress = userEmailAddress,
            onLogInClick = { authViewModel.signIn(context) },
            onLogOutClick = { authViewModel.signOut(context) },
            toggleLogInEnabled = isLogInEnabled,
        )

        SettingsScreen(
            onBackButtonClick = navController::popBackStack,
            userSettingParams = userSettingParams,
            onControllerSetupEntryClick = navController::navigateToControllerSetup,
            isPacingEnabled = isPacingEnabled,
            onSwitchPacing = preferenceViewModel::setPacingPreference,
        )
    }

}
