package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class SHFViewModel : ViewModel() {

    var shfEvent: String? by mutableStateOf(null)

    fun setSHFEvent(shfEvent: String?) {
        this.shfEvent = shfEvent
    }

}
