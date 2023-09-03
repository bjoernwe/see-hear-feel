package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.upaya.shf.ui.settings.controller.controllerSetupScreen
import dev.upaya.shf.ui.session.notingGraph
import dev.upaya.shf.ui.settings.settingsScreen
import dev.upaya.shf.ui.start.routeStartScreen
import dev.upaya.shf.ui.start.startScreen


@Composable
fun SHFNavHost(
    navController: NavHostController = rememberNavController(),
    startUserInteractionForSession: (Boolean) -> Unit = {},
    stopUserInteractionForSession: () -> Unit = {},
    showAccessibilitySettings: () -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = routeStartScreen,
    ) {

        controllerSetupScreen(
            navController = navController,
        )

        startScreen(
            navController = navController,
        )

        notingGraph(
            navController = navController,
            startUserInteractionForSession = startUserInteractionForSession,
            stopUserInteractionForSession = stopUserInteractionForSession,
        )

        settingsScreen(
            navController = navController,
            showAccessibilitySettings = showAccessibilitySettings,
        )

    }

}
