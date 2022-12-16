package dev.upaya.shf.session

import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.ExerciseID
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ActiveSessionSource @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
) {

    private val _exerciseConfig: MutableStateFlow<ExerciseConfig?> = MutableStateFlow(null)
    val exerciseConfig: StateFlow<ExerciseConfig?> = _exerciseConfig

    fun beginSession(exerciseID: ExerciseID) {
        _exerciseConfig.value = exerciseRepository.getExerciseConfig(exerciseID = exerciseID)
    }

    fun endSession() {
        _exerciseConfig.value = null
    }

}
