package dev.upaya.shf.inputs

import kotlinx.coroutines.flow.SharedFlow


interface IInputKeySource {
    val inputKeyDown: SharedFlow<InputKey>
    val inputKeyUp: SharedFlow<InputKey>
}
