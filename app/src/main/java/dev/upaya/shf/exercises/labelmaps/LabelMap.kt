package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.inputs.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): String
}
