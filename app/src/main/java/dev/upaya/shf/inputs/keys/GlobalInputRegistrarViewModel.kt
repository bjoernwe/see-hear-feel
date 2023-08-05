package dev.upaya.shf.inputs.keys

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GlobalInputRegistrarViewModel @Inject constructor(
    private val globalInputRegistrarSwitch: GlobalInputRegistrarSwitch,
) : ViewModel() {

    fun switchOn() {
        globalInputRegistrarSwitch.switchOn()
    }

    fun switchOff() {
        globalInputRegistrarSwitch.switchOff()
    }

}
