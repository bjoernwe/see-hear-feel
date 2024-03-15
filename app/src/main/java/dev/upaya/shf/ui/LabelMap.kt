package dev.upaya.shf.ui

import dev.upaya.shf.data.input.GamepadKey


fun interface LabelMap {
    fun getLabel(key: GamepadKey): Label
}
