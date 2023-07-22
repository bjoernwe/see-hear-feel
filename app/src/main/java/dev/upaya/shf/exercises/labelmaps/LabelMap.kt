package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_keys.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): Label
}
