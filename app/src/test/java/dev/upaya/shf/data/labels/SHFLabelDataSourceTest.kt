package dev.upaya.shf.data.labels

import android.view.KeyEvent
import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SHFLabelDataSourceTest {

    private lateinit var gamepadKeyEventDataSource: GamepadKeyEventDataSource
    private lateinit var unitUnderTest: SHFLabelDataSource

    @Before
    fun setUp() {
        gamepadKeyEventDataSource = GamepadKeyEventDataSource()
        unitUnderTest = SHFLabelDataSource(gamepadKeyEventDataSource = gamepadKeyEventDataSource)
    }

    @Test
    fun getLabelFlow_numberOfEmittedEvents_matchNumberOfInputs() = runTest {

        // GIVEN a label flow with a consumer
        val consumedValues = mutableListOf<SHFLabelEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            unitUnderTest.labelFlow.toList(consumedValues)
        }

        // WHEN one button is pressed
        gamepadKeyEventDataSource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN the consumer receives exactly one label
        assertEquals(1, consumedValues.size)
    }
}
