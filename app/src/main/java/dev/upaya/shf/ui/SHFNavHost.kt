package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.upaya.shf.ui.controller.controllerSetupScreen
import dev.upaya.shf.ui.controller.navigateToControllerSetup
import dev.upaya.shf.ui.exercises.exerciseListScreen
import dev.upaya.shf.ui.exercises.routeExerciseList
import dev.upaya.shf.ui.session.noting.navigateToNoting
import dev.upaya.shf.ui.session.noting.notingGraph
import dev.upaya.shf.ui.settings.settingsScreen


@Composable
fun SHFNavHost(
    navController: NavHostController = rememberNavController(),
    onSessionStart: () -> Unit = {},
    onSessionStop: () -> Unit = {},
    showAccessibilitySettings: () -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = routeExerciseList,
    ) {

        controllerSetupScreen()

        exerciseListScreen(
            onExerciseClick = { exerciseId -> navController.navigateToNoting(exerciseId) },
            onControllerButtonClick = navController::navigateToControllerSetup,
        )

        notingGraph(
            navController = navController,
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
        )

        settingsScreen(
            showAccessibilitySettings = showAccessibilitySettings,
        )

    }

}
