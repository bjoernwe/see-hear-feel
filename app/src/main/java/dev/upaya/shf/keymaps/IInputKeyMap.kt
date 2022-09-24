package dev.upaya.shf.keymaps

interface IInputKeyMap {
    fun getInputKey(keyCode: Int): InputKey?
}
