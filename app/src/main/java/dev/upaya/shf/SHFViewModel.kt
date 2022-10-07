package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.*
import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


@HiltViewModel
class SHFViewModel @Inject constructor(
    private val inputEventSource: InputEventSource,
) : ViewModel() {

    val inputEvent: StateFlow<InputEvent?> = inputEventSource.inputEvent
    var label = inputEvent.transform { inputEvent ->
        if (inputEvent != null) {
            emit(activeLabelMap.getLabel(inputEvent.inputKey))
        }
    }

    private var activeLabelMap: LabelMap by mutableStateOf(LabelMapSHF)

    fun activateLabelMap(labelMap: LabelMap) {
        activeLabelMap = labelMap

    }

    fun resetInputEvent() {
        inputEventSource.resetInputEvent()
    }

}
