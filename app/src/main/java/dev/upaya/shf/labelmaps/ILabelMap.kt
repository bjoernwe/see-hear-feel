package dev.upaya.shf.labelmaps

import dev.upaya.shf.keymaps.InputKey


interface ILabelMap {
    fun getLabel(key: InputKey): String
}
