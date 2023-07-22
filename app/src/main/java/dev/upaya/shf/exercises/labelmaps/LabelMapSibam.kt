package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.input_keys.InputKey


class LabelMapSibam {

    companion object : LabelMap {

        private val labelSomatic = Label("Somatic")
        private val labelImage = Label("Image")
        private val labelBehavior = Label("Behavior")
        private val labelAffect = Label("Affect")
        private val labelMeaning = Label("Meaning")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_LEFT -> labelSomatic
                InputKey.KEY_DOWN -> labelImage
                InputKey.KEY_RIGHT -> labelBehavior
                InputKey.KEY_UP -> labelAffect
                InputKey.KEY_L1 -> labelMeaning
                InputKey.KEY_L2 -> labelMeaning
                InputKey.KEY_A -> labelSomatic
                InputKey.KEY_B -> labelImage
                InputKey.KEY_X -> labelBehavior
                InputKey.KEY_Y -> labelAffect
                InputKey.KEY_R1 -> labelMeaning
                InputKey.KEY_R2 -> labelMeaning
                else -> labelOther
            }
        }

    }

}
