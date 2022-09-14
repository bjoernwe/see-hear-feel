package dev.upaya.shf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class SHFViewModel : ViewModel() {

    var keyEvent by mutableStateOf("N/A")

    fun setKey(key: String) {
        keyEvent = key
    }

}
