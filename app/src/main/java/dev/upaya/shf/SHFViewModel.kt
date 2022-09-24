package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.upaya.shf.keymaps.InputKey
import dev.upaya.shf.keymaps.SHFLabels


class SHFViewModel : ViewModel() {

    var lastSHFLabel: String? by mutableStateOf(null)

    fun setInputKey(inputKey: InputKey) {
        this.lastSHFLabel = SHFLabels.getLabel(inputKey)
    }

}
