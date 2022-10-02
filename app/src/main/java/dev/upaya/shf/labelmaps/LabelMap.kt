package dev.upaya.shf.labelmaps

import dev.upaya.shf.keymaps.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): String
}
