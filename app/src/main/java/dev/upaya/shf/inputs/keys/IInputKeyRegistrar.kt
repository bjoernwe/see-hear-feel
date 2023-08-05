package dev.upaya.shf.inputs.keys

import kotlinx.coroutines.flow.SharedFlow


interface IInputKeyRegistrar {

    val inputKeyDown: SharedFlow<InputKey>
    val inputKeyUp: SharedFlow<InputKey>

    /**
     * Should return false in two cases:
     * 1) When the submitted key is not mapped to SHF functionality
     * 2) When input keys are globally disabled because no session is running
     */
    fun registerKeyDown(keyCode: Int): Boolean

    fun registerKeyUp(keyCode: Int): Boolean

}
