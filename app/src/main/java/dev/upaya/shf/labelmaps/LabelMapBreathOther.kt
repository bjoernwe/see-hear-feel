package dev.upaya.shf.labelmaps

import dev.upaya.shf.input_devices.InputKey


class LabelMapBreathOther {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "BREATH"
                else -> "OTHER"
            }
        }
    }
}
