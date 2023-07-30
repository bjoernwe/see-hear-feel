package dev.upaya.shf.exercises.labelmaps

import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelOther
import dev.upaya.shf.inputs.keys.InputKey


class LabelMapLetGo {

    companion object : LabelMap {

        private val labelLetGo = Label("Letting Go")

        override fun getLabel(key: InputKey): Label {
            return when(key) {
                InputKey.KEY_A -> labelLetGo
                InputKey.KEY_UP -> labelLetGo
                InputKey.KEY_DOWN -> labelLetGo
                else -> labelOther
            }
        }

    }

}
