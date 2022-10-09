package dev.upaya.shf.exercises.labels

import dev.upaya.shf.inputs.InputKey


class LabelMapPosNegNeutral {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_UP -> "POSITIVE"
                InputKey.KEY_DOWN -> "NEGATIVE"
                InputKey.KEY_LEFT -> "NEUTRAL"
                InputKey.KEY_RIGHT -> "NEUTRAL"
                InputKey.KEY_A -> "NEGATIVE"
                InputKey.KEY_B -> "NEUTRAL"
                InputKey.KEY_X -> "NEUTRAL"
                InputKey.KEY_Y -> "POSITIVE"
                else -> "OTHER"
            }
        }
    }
}
