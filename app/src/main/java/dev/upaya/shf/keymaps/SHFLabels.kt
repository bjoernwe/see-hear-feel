package dev.upaya.shf.keymaps


class SHFLabels {
    companion object : IKeyLabelMap {
        override fun getLabel(key: InputKey): String {
            return when(key) {
                InputKey.KEY_1 -> "SEE"
                InputKey.KEY_2 -> "HEAR"
                InputKey.KEY_3 -> "FEEL"
            }
        }
    }
}
