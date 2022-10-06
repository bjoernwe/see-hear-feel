package dev.upaya.shf.labelmaps

import dev.upaya.shf.inputs.InputKey


class LabelMapSHF {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "SEE"
                InputKey.KEY_2 -> "HEAR"
                InputKey.KEY_3 -> "FEEL"
            }
        }
    }
}
