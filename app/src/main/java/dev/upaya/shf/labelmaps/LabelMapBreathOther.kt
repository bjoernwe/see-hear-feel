package dev.upaya.shf.labelmaps

import dev.upaya.shf.keymaps.InputKey


class LabelMapBreathOther {
    companion object : ILabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "BREATH"
                else -> "OTHER"
            }
        }
    }
}
