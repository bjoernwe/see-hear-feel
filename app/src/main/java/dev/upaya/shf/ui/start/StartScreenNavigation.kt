package dev.upaya.shf.ui.start

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.session.navigateToNoting
import dev.upaya.shf.ui.settings.navigateToSettings


internal const val routeStartScreen = "start_screen"


fun NavGraphBuilder.startScreen(
    navController: NavController,
) {

    composable(route = routeStartScreen) {

        val context = LocalContext.current
        val authViewModel: AuthViewModel = hiltViewModel()
        val userEmail by authViewModel.userEmail.collectAsState()
        val isLoginEnabled by authViewModel.isLoginEnabled.collectAsState()

        StartScreen(
            onStartButtonClick = navController::navigateToNoting,
            onSettingsButtonClick = navController::navigateToSettings,
            onSignInButtonClick = { authViewModel.signIn(context) },
            signedInUserEmail = userEmail,
            isLoginEnabled = isLoginEnabled,
        )
        
    }
    
}
