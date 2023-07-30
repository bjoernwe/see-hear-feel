package dev.upaya.shf.inputs.keys

import kotlinx.coroutines.flow.SharedFlow


interface IInputKeySource {
    val inputKeyDown: SharedFlow<InputKey>
    val inputKeyUp: SharedFlow<InputKey>
}
