package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.upaya.shf.keymaps.InputKey
import dev.upaya.shf.labelmaps.ILabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF


class SHFViewModel : ViewModel() {

    var activeLabelMap: ILabelMap by mutableStateOf(LabelMapSHF)
    var lastInputKey: InputKey? by mutableStateOf(null)

    fun setInputKey(inputKey: InputKey) {
        lastInputKey = inputKey
    }

    fun activateLabelMap(labelMap: ILabelMap) {
        activeLabelMap = labelMap
    }

}
