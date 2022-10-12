package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapSHFAuditor {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_LEFT -> "SEE"
                InputKey.KEY_RIGHT -> "HEAR"
                InputKey.KEY_DOWN -> "FEEL"
                InputKey.KEY_UP -> "GONE"
                InputKey.KEY_A -> "CLARITY (CENTER)"
                InputKey.KEY_B -> "EQUAN. (CENTER)"
                InputKey.KEY_X -> "CLARITY (PERIPH.)"
                InputKey.KEY_Y -> "EQUAN. (PERIPH.)"
                else -> "OTHER"
            }
        }
    }
}
