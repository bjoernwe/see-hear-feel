package dev.upaya.shf.exercises.labels

import dev.upaya.shf.inputs.InputKey


interface LabelMap {
    fun getLabel(key: InputKey): String
}