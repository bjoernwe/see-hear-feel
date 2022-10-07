package dev.upaya.shf.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapPosNegNeutral {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "POSITIVE"
                InputKey.KEY_2 -> "NEGATIVE"
                InputKey.KEY_3 -> "NEUTRAL"
            }
        }
    }
}
