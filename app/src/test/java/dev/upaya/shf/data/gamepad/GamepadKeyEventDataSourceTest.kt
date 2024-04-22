package dev.upaya.shf.data.gamepad

import android.view.KeyEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.Instant


@OptIn(ExperimentalCoroutinesApi::class)
class GamepadKeyEventDataSourceTest {

    private lateinit var unitUnderTest: GamepadKeyEventDataSource

    @Before
    fun setUp() {
        unitUnderTest = GamepadKeyEventDataSource()
    }

    @Test
    fun inputKeyDown_multipleConsumers_shareFlow() = runTest {

        // GIVEN an KeyPressDataSource
        // AND GIVEN two consumers (reading into lists)
        val consumedValues1 = mutableListOf<GamepadKeyEvent>()
        val consumedValues2 = mutableListOf<GamepadKeyEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) { unitUnderTest.inputKeyDown.toList(consumedValues1) }
        backgroundScope.launch(dispatcher) { unitUnderTest.inputKeyDown.toList(consumedValues2) }

        // WHEN the two consumers listen to the same event
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN the event is received by both consumers, i.e., the flow is shared
        // (there's one more event because the flow starts with UNMAPPED)
        assertEquals(2, consumedValues1.size)
        assertEquals(2, consumedValues2.size)
    }

    @Test
    fun keyDownEvent_repeatedButton_isEmittedAgain() = runTest {

        // GIVEN an InputEventDataSource
        // AND GIVEN a consumer (reading into a list)
        val consumedValues = mutableListOf<GamepadKeyEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            unitUnderTest.inputKeyDown.toList(consumedValues)
        }

        // WHEN a button is pressed twice
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        unitUnderTest.registerKeyUp(KeyEvent.KEYCODE_BUTTON_A)
        delay(1)
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN the event is emitted again
        assertEquals(3, consumedValues.size)
    }

    @Test
    fun keyDownEvent_continuedButtonPress_isEmittedOnce() = runTest {

        // GIVEN an InputEventDataSource
        // AND GIVEN a consumer (reading into a list)
        val consumedValues = mutableListOf<GamepadKeyEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            unitUnderTest.inputKeyDown.toList(consumedValues)
        }

        // WHEN a button is hold down
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN the event was emitted only once
        // (there's one more event because the flow starts with UNMAPPED)
        assertEquals(2, consumedValues.size)
    }

    @Test
    fun keyDownEvent_emittedEvent_containsCurrentTimeStamp() = runTest {

        // GIVEN an InputEventDataSource
        // AND GIVEN a consumer (reading into a list)
        val emittedValues = mutableListOf<GamepadKeyEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            unitUnderTest.inputKeyDown.toList(emittedValues)
        }

        // WHEN there is an event emitted
        unitUnderTest.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it contains the pressed key plus a current time stamp
        // (there's one more event because the flow starts with UNMAPPED)
        assertEquals(2, emittedValues.size)
        val eventTimeInSeconds = emittedValues[1].timestamp.epochSecond
        val nowInSeconds = Instant.now().epochSecond
        assertEquals(nowInSeconds, eventTimeInSeconds)
    }

}
