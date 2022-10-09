package dev.upaya.shf.exercises.labels

import dev.upaya.shf.inputs.InputKey


class LabelMapSHF {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_LEFT -> "SEE"
                InputKey.KEY_RIGHT -> "HEAR"
                InputKey.KEY_DOWN -> "FEEL"
                InputKey.KEY_UP -> "GONE"
                InputKey.KEY_A -> "SEE"
                InputKey.KEY_B -> "HEAR"
                InputKey.KEY_X -> "FEEL"
                InputKey.KEY_Y -> "GONE"
                else -> "OTHER"
            }
        }
    }
}
