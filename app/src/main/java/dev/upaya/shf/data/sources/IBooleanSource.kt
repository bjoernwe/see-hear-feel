package dev.upaya.shf.data.sources

import kotlinx.coroutines.flow.StateFlow


interface IBooleanSource {
    val isEnabled: StateFlow<Boolean>
}
