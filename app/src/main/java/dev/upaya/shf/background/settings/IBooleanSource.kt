package dev.upaya.shf.background.settings

import kotlinx.coroutines.flow.StateFlow


interface IBooleanSource {
    val value: StateFlow<Boolean>
}
