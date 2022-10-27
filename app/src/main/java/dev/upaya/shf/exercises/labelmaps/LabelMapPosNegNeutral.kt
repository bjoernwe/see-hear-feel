package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.InputKey


class LabelMapPosNegNeutral {

    companion object : LabelMap {

        private val labelPositive = Label("Positive")
        private val labelNegative = Label("Negative")
        private val labelNeutral = Label("Neutral")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_UP -> labelPositive
                InputKey.KEY_DOWN -> labelNegative
                InputKey.KEY_LEFT -> labelNeutral
                InputKey.KEY_RIGHT -> labelNeutral
                InputKey.KEY_A -> labelNegative
                InputKey.KEY_B -> labelNeutral
                InputKey.KEY_X -> labelNeutral
                InputKey.KEY_Y -> labelPositive
                else -> labelOther
            }
        }

    }

}
