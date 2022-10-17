package dev.upaya.shf.ui.exercises

import androidx.lifecycle.ViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository


class ExerciseListViewModel: ViewModel() {
    private val exerciseRepository = ExerciseRepository()
    val exercises = exerciseRepository.exercises
}
