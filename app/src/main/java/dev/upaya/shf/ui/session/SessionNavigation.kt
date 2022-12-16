package dev.upaya.shf.ui.session

import androidx.navigation.NavController
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute


fun NavController.navigateToExerciseSession(
    exerciseRoute: ExerciseRoute,
    exerciseID: ExerciseID,
) {
    this.navigate(exerciseRoute.name)
}
