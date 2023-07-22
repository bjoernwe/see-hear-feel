package dev.upaya.shf.inputs


interface IInputKeyRegistrar {

    fun registerKeyDown(keyCode: Int): Boolean
    fun registerKeyUp(keyCode: Int): Boolean

    /**
     * Will accept input keys
     */
    fun enableRegistrar()

    /**
     * Will ignore incoming input keys, i.e., return false
     */
    fun disableRegistrar()

}
