package dev.upaya.shf.inputs


interface IInputKeyRegistrar {
    fun registerKeyDown(keyCode: Int): Boolean
    fun registerKeyUp(keyCode: Int): Boolean
}
