package dev.upaya.shf.ui.exercises

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.ExerciseRepository
import javax.inject.Inject


@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
): ViewModel() {

    fun getExercises(): Map<String, ExerciseConfig> {
        return exerciseRepository.getExercises()
    }

    fun getExerciseConfig(exerciseID: String): ExerciseConfig? {
        return exerciseRepository.getExerciseConfig(exerciseID = exerciseID)
    }

}
