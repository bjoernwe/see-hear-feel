package dev.upaya.shf.inputs.input_keys

import android.view.KeyEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InputKeyRegistrarTest {

    private lateinit var inputKeyRegistrar: IInputKeyRegistrar

    @Before
    fun setUp() {
        inputKeyRegistrar = InputKeyRegistrar().apply { enableRegistrar() }
    }

    /*
    Test that key source actually emits updates even when the same key is pressed multiple times in
    a row (StateFlow doesn't do this).

    For documentation see:
    - https://developer.android.com/kotlin/flow/test#continuous-collection
    - https://stackoverflow.com/a/65909194/1261573
    */
    @Test
    fun registerKeyDown_registeringKeyTwice_emitsTwice() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyDown.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key with release in between
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted twice
        val numEmittedValues = emittedValues.size
        Assert.assertEquals(2, numEmittedValues)
    }

    @Test
    fun registerKeyDown_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyDown.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN nothing is emitted
        val numEmittedValues = emittedValues.size
        Assert.assertEquals(0, numEmittedValues)
    }

    @Test
    fun registerKeyUp_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyUp.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputKeyRegistrar.registerKeyUp(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN it is not emitted
        val numEmittedValues = emittedValues.size
        Assert.assertEquals(0, numEmittedValues)
    }

    @Test
    fun registerKeyUp_registeringKeyTwice_emitsTwice() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyUp.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key with release in between
        inputKeyRegistrar.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)
        inputKeyRegistrar.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted twice
        val numEmittedValues = emittedValues.size
        Assert.assertEquals(2, numEmittedValues)
    }

    @Test
    fun registerKeyDown_multipleConsumers_shareFlow() = runTest {

        // GIVEN a InputSource and two consumers
        val consumedValues1 = mutableListOf<InputKey>()
        val consumedValues2 = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyDown.toList(consumedValues1)
        }
        backgroundScope.launch(dispatcher) {
            inputKeyRegistrar.inputKeyDown.toList(consumedValues2)
        }

        // WHEN a key is registered two times
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN both consumers receive both events
        Assert.assertEquals(2, consumedValues1.size)
        Assert.assertEquals(2, consumedValues2.size)
    }

}
