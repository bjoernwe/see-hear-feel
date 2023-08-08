package dev.upaya.shf.ui.start

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.ui.session.noting.navigateToNoting
import dev.upaya.shf.ui.settings.navigateToSettings


internal const val routeStartScreen = "start_screen"


fun NavController.navigateToStartScreen() {
    navigate(routeStartScreen)
}


fun NavGraphBuilder.startScreen(
    navController: NavController,
) {

    composable(route = routeStartScreen) {
        
        StartScreen(
            onStartButtonClick = { navController.navigateToNoting(ExerciseId.SHF) },
            onSettingsButtonClick = navController::navigateToSettings,
        )
        
    }
    
}
