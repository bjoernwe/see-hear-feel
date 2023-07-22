package dev.upaya.shf.inputs.input_keys


interface IInputKeyRegistrar : IInputKeySource {

    /**
     * Will accept input keys
     */
    fun enableRegistrar()

    /**
     * Will ignore incoming input keys, i.e., not pass on to source
     */
    fun disableRegistrar()

    /**
     * Returns false when disabled
     */
    fun registerKeyDown(keyCode: Int): Boolean

    /**
     * Returns false when disabled
     */
    fun registerKeyUp(keyCode: Int): Boolean

}
