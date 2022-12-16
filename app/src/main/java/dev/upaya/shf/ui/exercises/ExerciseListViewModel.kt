package dev.upaya.shf.ui.exercises

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
): ViewModel() {

    val exercises: StateFlow<Map<String, ExerciseConfig>> = exerciseRepository.exercises

    fun getExerciseConfig(exerciseID: String): ExerciseConfig? {
        return exerciseRepository.getExerciseConfig(exerciseID = exerciseID)
    }

}
