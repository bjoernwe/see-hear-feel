package dev.upaya.shf.ui.exercises

import androidx.navigation.NavController
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.exerciselist.ExerciseRoute


fun NavController.navigateToExercise(
    exerciseRoute: ExerciseRoute,
    exerciseID: ExerciseID,
) {
    this.navigate(exerciseRoute.name)
}
