package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapLetGo {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_A -> "LET GO"
                InputKey.KEY_UP -> "LET GO"
                InputKey.KEY_DOWN -> "LET GO"
                else -> "OTHER"
            }
        }
    }
}
