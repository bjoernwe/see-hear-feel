package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.keys.InputKey


class LabelMapBreathOther {

    companion object : LabelMap {

        private val labelBreath = Label("Breath")
        private val labelDistraction = Label("Distraction")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_A -> labelBreath
                InputKey.KEY_B -> labelDistraction
                InputKey.KEY_X -> labelDistraction
                InputKey.KEY_Y -> labelBreath
                InputKey.KEY_UP -> labelBreath
                InputKey.KEY_DOWN -> labelBreath
                InputKey.KEY_LEFT -> labelDistraction
                InputKey.KEY_RIGHT -> labelDistraction
                else -> labelOther
            }
        }

    }

}
