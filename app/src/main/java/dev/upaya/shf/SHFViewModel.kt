package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.upaya.shf.input_devices.InputKey
import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF


class SHFViewModel : ViewModel() {

    var activeLabelMap: LabelMap by mutableStateOf(LabelMapSHF)
    var lastInputKey: InputKey? by mutableStateOf(null)

    fun setInputKey(inputKey: InputKey) {
        lastInputKey = inputKey
    }

    fun activateLabelMap(labelMap: LabelMap) {
        activeLabelMap = labelMap
    }

}
