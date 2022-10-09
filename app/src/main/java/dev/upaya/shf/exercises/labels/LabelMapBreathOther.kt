package dev.upaya.shf.exercises.labels

import dev.upaya.shf.inputs.InputKey


class LabelMapBreathOther {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_A -> "BREATH"
                InputKey.KEY_B -> "DISTRACT"
                InputKey.KEY_X -> "DISTRACT"
                InputKey.KEY_Y -> "BREATH"
                InputKey.KEY_UP -> "BREATH"
                InputKey.KEY_DOWN -> "BREATH"
                InputKey.KEY_LEFT -> "DISTRACT"
                InputKey.KEY_RIGHT -> "DISTRACT"
                else -> "OTHER"
            }
        }
    }
}
