package dev.upaya.shf.ui

import dev.upaya.shf.data.sources.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): Label
}
