package dev.upaya.shf.inputs

interface InputDevice {
    fun getInputKey(keyCode: Int): InputKey
}
