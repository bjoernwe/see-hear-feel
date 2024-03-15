package dev.upaya.shf.ui

import dev.upaya.shf.data.gamepad.GamepadKey


fun interface LabelMap {
    fun getLabel(key: GamepadKey): Label
}
