package dev.upaya.shf.session

import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ActiveSessionSource @Inject constructor() {

    private val _exerciseConfig: MutableStateFlow<ExerciseConfig?> = MutableStateFlow(null)
    val exerciseConfig: StateFlow<ExerciseConfig?> = _exerciseConfig

    val sessionActive: Flow<Boolean> = exerciseConfig.transform { cfg ->
        emit(cfg != null)
    }

    fun beginSession(exerciseConfig: ExerciseConfig) {
        _exerciseConfig.value = exerciseConfig
    }

    fun endSession() {
        _exerciseConfig.value = null
    }

}
