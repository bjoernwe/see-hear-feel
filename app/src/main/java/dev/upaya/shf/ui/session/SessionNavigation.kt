package dev.upaya.shf.ui.session

import androidx.navigation.NavController
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute
import dev.upaya.shf.ui.session.feelings.navigateToFeelingsSession
import dev.upaya.shf.ui.session.noting.navigateToNoting


fun NavController.navigateToExerciseSession(
    exerciseRoute: ExerciseRoute,
    exerciseID: ExerciseID,
) {
    when (exerciseRoute) {
        ExerciseRoute.NOTING -> navigateToNoting(exerciseID)
        ExerciseRoute.FEELINGS -> navigateToFeelingsSession()
    }
}
