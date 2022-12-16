package dev.upaya.shf.exercises.exerciselist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepository @Inject constructor() {

    val exercises: StateFlow<Map<ExerciseID, ExerciseConfig>> = MutableStateFlow(exampleExercises)

    fun getExerciseConfig(exerciseID: ExerciseID): ExerciseConfig? {
        return exampleExercises[exerciseID]
    }

}
