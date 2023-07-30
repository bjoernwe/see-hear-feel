package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.*
import dev.upaya.shf.inputs.keys.InputKey


class LabelMapSHF {
    companion object : LabelMap {
        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_LEFT -> labelSee
                InputKey.KEY_RIGHT -> labelHear
                InputKey.KEY_DOWN -> labelFeel
                InputKey.KEY_UP -> labelGone
                InputKey.KEY_A -> labelHear
                InputKey.KEY_B -> labelSee
                InputKey.KEY_X -> labelFeel
                InputKey.KEY_Y -> labelGone
                else -> labelOther
            }
        }
    }
}
