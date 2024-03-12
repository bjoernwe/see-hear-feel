package dev.upaya.shf.data.sessionhistory

import dev.upaya.shf.data.input.InputKey


class SHFLabelMap {
    companion object {
        fun getLabel(key: InputKey): SHFLabel? = when(key) {
            InputKey.KEY_LEFT -> SHFLabel.SEE
            InputKey.KEY_RIGHT -> SHFLabel.HEAR
            InputKey.KEY_DOWN -> SHFLabel.FEEL
            InputKey.KEY_UP -> SHFLabel.GONE
            InputKey.KEY_A -> SHFLabel.SEE
            InputKey.KEY_B -> SHFLabel.HEAR
            InputKey.KEY_X -> SHFLabel.GONE
            InputKey.KEY_Y -> SHFLabel.FEEL
            else -> null
        }
    }
}
