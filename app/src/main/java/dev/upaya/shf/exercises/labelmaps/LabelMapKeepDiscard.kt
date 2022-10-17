package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.InputKey


class LabelMapKeepDiscard {

    companion object : LabelMap {

        val labelKeep = Label("Keep")
        val labelDiscard = Label("Discard")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_A -> labelKeep
                InputKey.KEY_B -> labelDiscard
                InputKey.KEY_UP -> labelKeep
                InputKey.KEY_DOWN -> labelDiscard
                else -> labelOther
            }
        }

    }

}
