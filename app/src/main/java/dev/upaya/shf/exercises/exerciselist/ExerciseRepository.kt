package dev.upaya.shf.exercises.exerciselist

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepository @Inject constructor() {

    fun getExercises(): Map<String, ExerciseConfig> {
        return exampleExercises
    }

    fun getExerciseConfig(exerciseID: String): ExerciseConfig? {
        return exampleExercises[exerciseID]
    }

}
