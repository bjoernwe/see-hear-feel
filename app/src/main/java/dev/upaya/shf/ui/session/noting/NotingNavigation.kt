package dev.upaya.shf.ui.session.noting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.ui.session.noting.session.notingScreen
import dev.upaya.shf.ui.session.noting.session.routeNotingSession
import dev.upaya.shf.ui.session.noting.stats.navigateToNotingStats
import dev.upaya.shf.ui.session.noting.stats.notingStatsScreen


private const val routeNotingGraph = "noting_graph"

internal const val routeArgExerciseId = "exerciseId"
internal const val routeNotingGraphWithArg = "$routeNotingGraph/{$routeArgExerciseId}"


fun NavController.navigateToNoting(exerciseId: ExerciseId) {
    navigate("${routeNotingGraph}/${exerciseId}")
}


fun NavGraphBuilder.notingGraph(
    navController: NavController,
) {
    
    navigation(
        route = routeNotingGraphWithArg,
        startDestination = routeNotingSession,
    ) {

        notingScreen(
            navController = navController,
            onStopButtonClick = {
                navController.navigateToNotingStats()
            },
        )

        notingStatsScreen(
            navController = navController,
        )

    }
    
}
