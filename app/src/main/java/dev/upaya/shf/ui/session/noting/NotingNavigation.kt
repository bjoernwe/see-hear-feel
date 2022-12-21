package dev.upaya.shf.ui.session.noting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.ui.session.noting.intros.notingIntroScreen
import dev.upaya.shf.ui.session.noting.intros.routeNotingIntro
import dev.upaya.shf.ui.session.noting.session.notingSessionScreen
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
        startDestination = routeNotingIntro,
    ) {

        notingIntroScreen(
            navController = navController,
        )

        notingSessionScreen(
            navController = navController,
            onStopButtonClick = {
                navController.popBackStack()
                navController.navigateToNotingStats()
            },
        )

        notingStatsScreen(
            navController = navController,
        )

    }
    
}


@Composable
internal fun getScopedSessionViewModel(
    routeForScope: String,
    backStackEntry: NavBackStackEntry,
    navController: NavController,
): SessionViewModel {

    val sessionScope = remember(backStackEntry) {
        navController.getBackStackEntry(routeForScope)
    }

    return hiltViewModel(viewModelStoreOwner = sessionScope)
}
