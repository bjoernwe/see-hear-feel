package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.*
import dev.upaya.shf.inputs.input_keys.InputKey


class LabelMapSHFAuditor {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_LEFT -> labelSee
                InputKey.KEY_RIGHT -> labelHear
                InputKey.KEY_DOWN -> labelFeel
                InputKey.KEY_UP -> labelGone
                InputKey.KEY_A -> Label("Clarity", "Center")
                InputKey.KEY_B -> Label("Clarity", "Periphery")
                InputKey.KEY_X -> Label("Equanimity", "Center")
                InputKey.KEY_Y -> Label("Equanimity", "Periphery")
                else -> labelOther
            }
        }
    }
}
