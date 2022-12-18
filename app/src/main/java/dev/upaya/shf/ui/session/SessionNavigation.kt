package dev.upaya.shf.ui.session

import androidx.navigation.NavController
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.ui.session.feelings.navigateToFeelingsSession
import dev.upaya.shf.ui.session.noting.navigateToNoting


fun NavController.navigateToExerciseSession(
    exerciseID: ExerciseID,
) {
    when (exerciseID) {
        ExerciseID.CORE_FEELINGS -> navigateToFeelingsSession(exerciseID)
        else -> navigateToNoting(exerciseID)
    }
}
