package dev.upaya.shf.input_devices

interface InputDevice {
    fun getInputKey(keyCode: Int): InputKey?
}
