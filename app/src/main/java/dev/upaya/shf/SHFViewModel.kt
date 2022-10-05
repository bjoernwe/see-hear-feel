package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.upaya.shf.input_devices.InputDevice
import dev.upaya.shf.input_devices.InputDeviceHeadset
import dev.upaya.shf.input_devices.InputKey
import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF


class SHFViewModel : ViewModel() {

    private var activeLabelMap: LabelMap by mutableStateOf(LabelMapSHF)
    private var lastInputKey: InputKey? = null

    val inputDevice: InputDevice = InputDeviceHeadset
    var lastLabel: String by mutableStateOf("")

    fun setInputKey(inputKey: InputKey) {
        lastInputKey = inputKey
        lastLabel = activeLabelMap.getLabel(inputKey)
    }

    fun activateLabelMap(labelMap: LabelMap) {
        activeLabelMap = labelMap
    }

}
