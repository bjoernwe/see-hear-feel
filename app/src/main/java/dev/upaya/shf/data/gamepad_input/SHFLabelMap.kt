package dev.upaya.shf.data.gamepad_input


class SHFLabelMap {
    companion object {
        fun getLabel(key: GamepadKey): SHFLabel? = when(key) {
            GamepadKey.KEY_LEFT -> SHFLabel.SEE
            GamepadKey.KEY_RIGHT -> SHFLabel.HEAR
            GamepadKey.KEY_DOWN -> SHFLabel.FEEL
            GamepadKey.KEY_UP -> SHFLabel.GONE
            GamepadKey.KEY_A -> SHFLabel.SEE
            GamepadKey.KEY_B -> SHFLabel.HEAR
            GamepadKey.KEY_X -> SHFLabel.GONE
            GamepadKey.KEY_Y -> SHFLabel.FEEL
            else -> null
        }
    }
}
