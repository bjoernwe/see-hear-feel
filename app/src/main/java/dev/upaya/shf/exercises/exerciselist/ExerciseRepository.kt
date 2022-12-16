package dev.upaya.shf.exercises.exerciselist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ExerciseRepository {
    private val _exercises: MutableStateFlow<List<ExerciseConfig>> = MutableStateFlow(exampleExercises.values.toList())
    val exercises: StateFlow<List<ExerciseConfig>> = _exercises
}
