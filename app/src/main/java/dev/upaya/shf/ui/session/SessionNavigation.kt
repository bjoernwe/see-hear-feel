package dev.upaya.shf.ui.session

import androidx.navigation.NavController
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.ui.session.feelings.navigateToFeelingsSession
import dev.upaya.shf.ui.session.noting.navigateToNoting


fun NavController.navigateToExerciseSession(
    exerciseId: ExerciseId,
) {
    when (exerciseId) {
        ExerciseId.CORE_FEELINGS -> navigateToFeelingsSession(exerciseId)
        else -> navigateToNoting(exerciseId)
    }
}
