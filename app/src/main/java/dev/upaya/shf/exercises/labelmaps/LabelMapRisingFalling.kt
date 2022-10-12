package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapRisingFalling {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_A -> "RISING"
                InputKey.KEY_B -> "FALLING"
                InputKey.KEY_X -> "DISTRACT"
                InputKey.KEY_UP -> "RISING"
                InputKey.KEY_DOWN -> "FALLING"
                InputKey.KEY_LEFT -> "DISTRACT"
                InputKey.KEY_RIGHT -> "DISTRACT"
                else -> "OTHER"
            }
        }
    }
}
