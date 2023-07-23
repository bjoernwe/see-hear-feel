package dev.upaya.shf.inputs.input_keys


interface IInputKeyRegistrar : IInputKeySource {

    /**
     * Returns false when disabled
     */
    fun registerKeyDown(keyCode: Int): Boolean

    /**
     * Returns false when disabled
     */
    fun registerKeyUp(keyCode: Int): Boolean

}
