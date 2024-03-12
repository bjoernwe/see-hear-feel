package dev.upaya.shf.ui

import dev.upaya.shf.data.input.InputKey


fun interface LabelMap {
    fun getLabel(key: InputKey): Label
}
