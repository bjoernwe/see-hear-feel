package dev.upaya.shf.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapLetGo {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "LET GO"
                else -> "OTHER"
            }
        }
    }
}
