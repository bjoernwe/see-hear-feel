package dev.upaya.shf.ui.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LabelViewModel @Inject constructor(
    private val inputEventSource: InputEventSource,
    private val inputEventStats: InputEventStats,
) : ViewModel() {

    private var _currentExercise: MutableStateFlow<ExerciseConfig?> = MutableStateFlow(null)
    private val inputEvent: StateFlow<InputEvent?> = inputEventSource.inputEvent

    var label: Flow<Label> = transformInputEventToLabel(inputEvent)
    var labelStats = inputEventStats.labelFreqs

    init {
        initStatsCollection()
    }

    fun setCurrentExercise(exerciseConfig: ExerciseConfig) {
        inputEventSource.resetInputEvent()
        _currentExercise.value = exerciseConfig
    }

    private fun transformInputEventToLabel(
        inputEvent: StateFlow<InputEvent?>
    ): Flow<Label> {
        return inputEvent.transform { event ->
            if (event != null) {
                _currentExercise.value?.labelMap?.let { labelMap ->
                    emit(labelMap.getLabel(event.inputKey))
                }
            }
        }
    }

    private fun initStatsCollection() {
        viewModelScope.launch {
            label.collect() { label ->
                inputEventStats.reportInputEvent(label)
            }
        }
    }

}
