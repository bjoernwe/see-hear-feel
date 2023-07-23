package dev.upaya.shf.inputs.input_keys

import kotlinx.coroutines.flow.SharedFlow


interface IInputKeySource {
    val inputKeyDown: SharedFlow<InputKey>
    val inputKeyUp: SharedFlow<InputKey>
}
