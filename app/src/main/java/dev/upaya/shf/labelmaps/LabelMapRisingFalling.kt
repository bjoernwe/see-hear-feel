package dev.upaya.shf.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapRisingFalling {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "RISING"
                InputKey.KEY_2 -> "FALLING"
                InputKey.KEY_3 -> "DISTRACT"
                else -> "OTHER"
            }
        }
    }
}
