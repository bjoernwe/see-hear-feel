package dev.upaya.shf.exercises.exerciselist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepository @Inject constructor() {

    val exercises: StateFlow<Map<ExerciseId, ExerciseConfig>> = MutableStateFlow(exampleExercises)

    fun getExerciseConfig(exerciseId: ExerciseId): ExerciseConfig? {
        return exampleExercises[exerciseId]
    }

}
