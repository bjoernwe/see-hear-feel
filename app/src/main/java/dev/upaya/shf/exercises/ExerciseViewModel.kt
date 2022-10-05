package dev.upaya.shf.exercises

import androidx.lifecycle.ViewModel


class ExerciseViewModel: ViewModel() {
    private val exerciseRepository = ExerciseRepository()
    val exercises = exerciseRepository.exercises
}
