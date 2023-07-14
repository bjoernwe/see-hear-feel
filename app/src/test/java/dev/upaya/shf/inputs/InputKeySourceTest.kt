package dev.upaya.shf.inputs

import android.view.KeyEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InputKeySourceTest {

    private lateinit var inputSource: InputKeySource

    @Before
    fun setUp() {
        inputSource = InputKeySource()
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
            inputSource.inputKeyDown.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key with release in between
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted twice
        val numEmittedValues = emittedValues.size
        assertEquals(2, numEmittedValues)
    }

    @Test
    fun registerKeyDown_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputKeyDown.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputSource.registerKeyDown(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN nothing is emitted
        val numEmittedValues = emittedValues.size
        assertEquals(0, numEmittedValues)
    }

    @Test
    fun registerKeyUp_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputKeyUp.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputSource.registerKeyUp(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN it is not emitted
        val numEmittedValues = emittedValues.size
        assertEquals(0, numEmittedValues)
    }

    @Test
    fun registerKeyUp_registeringKeyTwice_emitsTwice() = runTest {

        // GIVEN a InputSource and list of its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputKeyUp.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key with release in between
        inputSource.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)
        inputSource.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted twice
        val numEmittedValues = emittedValues.size
        assertEquals(2, numEmittedValues)
    }

    @Test
    fun registerKeyDown_multipleConsumers_shareFlow() = runTest {

        // GIVEN a InputSource and two consumers
        val consumedValues1 = mutableListOf<InputKey>()
        val consumedValues2 = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputKeyDown.toList(consumedValues1)
        }
        backgroundScope.launch(dispatcher) {
            inputSource.inputKeyDown.toList(consumedValues2)
        }

        // WHEN a key is registered two times
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN both consumers receive both events
        assertEquals(2, consumedValues1.size)
        assertEquals(2, consumedValues2.size)
    }

}
