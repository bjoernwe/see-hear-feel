package dev.upaya.shf.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapAuditor {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_LEFT -> "AUDITOR"
                InputKey.KEY_RIGHT -> "AUDITOR"
                InputKey.KEY_DOWN -> "MEDITATOR"
                InputKey.KEY_UP -> "MEDITATOR"
                InputKey.KEY_A -> "MEDITATOR"
                InputKey.KEY_B -> "AUDITOR"
                InputKey.KEY_X -> "MEDITATOR"
                InputKey.KEY_Y -> "AUDITOR"
                else -> "OTHER"
            }
        }
    }
}
