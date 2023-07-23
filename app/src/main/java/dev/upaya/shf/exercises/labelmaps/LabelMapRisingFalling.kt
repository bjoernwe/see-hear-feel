package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.input_keys.InputKey


class LabelMapRisingFalling {

    companion object : LabelMap {

        private val labelRising = Label("Rising")
        private val labelFalling = Label("Falling")
        private val labelDistraction = Label("Distraction")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_A -> labelRising
                InputKey.KEY_B -> labelFalling
                InputKey.KEY_X -> labelDistraction
                InputKey.KEY_UP -> labelRising
                InputKey.KEY_DOWN -> labelFalling
                InputKey.KEY_LEFT -> labelDistraction
                InputKey.KEY_RIGHT -> labelDistraction
                else -> labelOther
            }
        }

    }

}
