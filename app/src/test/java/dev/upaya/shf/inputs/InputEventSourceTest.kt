package dev.upaya.shf.inputs

import android.view.KeyEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InputEventSourceTest {

    private lateinit var inputSource: InputEventSource

    @Before
    fun setUp() {
        inputSource = InputEventSource()
    }

    /*
    Test that key source actually emits updates even when the same key is pressed multiple times in
    a row (StateFlow doesn't do this).

    For documentation see:
    - https://developer.android.com/kotlin/flow/test#continuous-collection
    - https://stackoverflow.com/a/65909194/1261573
    */
    @Test
    fun registerKeyDown_registeringKeyTwiceWithRelease_emitsTwice() = runTest {

        // GIVEN a GenericInputSource and list of its emitted values
        val emittedValues = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputEvent.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key with release in between
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputSource.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted twice
        val numEmittedValues = emittedValues.size
        assertEquals(2, numEmittedValues)
    }

    @Test
    fun registerKeyDown_registeringKeyMultipleTimesWithoutRelease_emitsOnce() = runTest {

        // GIVEN a GenericInputSource and list of its emitted values
        val emittedValues = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputEvent.toList(emittedValues)
        }

        // WHEN a key is registered two times in row without key release in between
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        withContext(Dispatchers.IO) {
            Thread.sleep(1) // KeyEvents have a temporal resolution of 1ms
        }
        inputSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it is emitted only once
        val numEmittedValues = emittedValues.size
        assertEquals(1, numEmittedValues)
    }

    @Test
    fun registerKeyDown_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a GenericInputSource and list of its emitted values
        val emittedValues = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputEvent.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputSource.registerKeyDown(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN it is emitted only once
        val numEmittedValues = emittedValues.size
        assertEquals(0, numEmittedValues)
    }

    @Test
    fun registerKeyUp_registeringUnmappedKey_doesNotEmit() = runTest {

        // GIVEN a GenericInputSource and list of its emitted values
        val emittedValues = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputSource.inputEvent.toList(emittedValues)
        }

        // WHEN an unmapped key is registered
        inputSource.registerKeyUp(KeyEvent.KEYCODE_ZENKAKU_HANKAKU)

        // THEN it is not emitted
        val numEmittedValues = emittedValues.size
        assertEquals(0, numEmittedValues)
    }

}
