package dev.upaya.shf.ui

import dev.upaya.shf.data.gamepad.GamepadKey


class LabelMapSHF {
    companion object : LabelMap {
        override fun getLabel(key: GamepadKey) = when(key) {
            GamepadKey.KEY_LEFT -> labelSee
            GamepadKey.KEY_RIGHT -> labelHear
            GamepadKey.KEY_DOWN -> labelFeel
            GamepadKey.KEY_UP -> labelGone
            GamepadKey.KEY_A -> labelSee
            GamepadKey.KEY_B -> labelHear
            GamepadKey.KEY_X -> labelGone
            GamepadKey.KEY_Y -> labelFeel
            else -> labelOther
        }
    }
}
