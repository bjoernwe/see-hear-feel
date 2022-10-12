package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.InputKey


class LabelMapAuditor {

    companion object : LabelMap {

        private val labelMeditator = Label("Meditator")
        private val labelAuditor = Label("Auditor")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_LEFT -> labelAuditor
                InputKey.KEY_RIGHT -> labelAuditor
                InputKey.KEY_DOWN -> labelMeditator
                InputKey.KEY_UP -> labelMeditator
                InputKey.KEY_A -> labelMeditator
                InputKey.KEY_B -> labelAuditor
                InputKey.KEY_X -> labelMeditator
                InputKey.KEY_Y -> labelAuditor
                else -> labelOther
            }
        }

    }

}
