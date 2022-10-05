package dev.upaya.shf.labelmaps

import dev.upaya.shf.input_devices.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): String
}
