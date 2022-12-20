package dev.upaya.shf.ui.session.noting.intros

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.ui.exercises.ExerciseListViewModel
import dev.upaya.shf.ui.session.noting.SessionViewModel
import dev.upaya.shf.ui.session.noting.getScopedSessionViewModel
import dev.upaya.shf.ui.session.noting.routeNotingGraphWithArg
import dev.upaya.shf.ui.session.noting.session.navigateToNotingSession


internal const val routeNotingIntro = "noting_intro"


internal fun NavGraphBuilder.notingIntroScreen(
    navController: NavController,
) {

    composable(routeNotingIntro) { backStackEntry ->

        val exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
        val sessionViewModel: SessionViewModel = getScopedSessionViewModel(
            routeForScope = routeNotingGraphWithArg,
            backStackEntry = backStackEntry,
            navController = navController
        )

        val exerciseId = sessionViewModel.exerciseId
        val exercises by exerciseListViewModel.exercises.collectAsState()
        val exercise = exercises.getValue(exerciseId)

        NotingIntroScreen(
            exercise = exercise,
            onClickButton = { navController.navigateToNotingSession() },
        )

    }

}
