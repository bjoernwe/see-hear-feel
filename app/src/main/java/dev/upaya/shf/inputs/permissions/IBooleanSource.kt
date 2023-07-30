package dev.upaya.shf.inputs.permissions

import kotlinx.coroutines.flow.StateFlow


interface IBooleanSource {
    val isEnabled: StateFlow<Boolean>
}
