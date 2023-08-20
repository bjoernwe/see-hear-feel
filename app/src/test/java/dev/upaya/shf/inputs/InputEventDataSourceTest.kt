package dev.upaya.shf.inputs

import android.view.KeyEvent
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.sources.InputEventDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class InputEventDataSourceTest {

    @Test
    fun registerKeyDown_multipleConsumers_shareFlow() = runTest {

        // GIVEN an InputEventSource with InputKeySource
        val globalInputRegistrarSwitch = GlobalInputRegistrarSwitch().apply { this.switchOn() }
        val inputKeyRegistrar: IInputKeyRegistrar = InputKeyRegistrar(
            globalInputRegistrarSwitch = globalInputRegistrarSwitch
        )
        val inputEventDataSource = InputEventDataSource(
            inputKeySource = inputKeyRegistrar as IInputKeySource,
            dispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        // AND GIVEN two consumers (reading into lists)
        val consumedValues1 = mutableListOf<InputEvent>()
        val consumedValues2 = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputEventDataSource.inputEvent.toList(consumedValues1)
        }
        backgroundScope.launch(dispatcher) {
            inputEventDataSource.inputEvent.toList(consumedValues2)
        }

        // WHEN there are two events
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN both events are received by both consumers (i.e., the flow is shared)
        assertEquals(2, consumedValues1.size)
        assertEquals(2, consumedValues2.size)
    }

    @Test
    fun registerKeyDown_emittedEvent_containsCurrentTimeStamp() = runTest {

        // GIVEN an InputEventSource with InputKeySource
        val globalInputRegistrarSwitch = GlobalInputRegistrarSwitch().apply { this.switchOn() }
        val inputKeyRegistrar: IInputKeyRegistrar = InputKeyRegistrar(
            globalInputRegistrarSwitch = globalInputRegistrarSwitch
        )
        val inputEventDataSource = InputEventDataSource(
            inputKeySource = inputKeyRegistrar as IInputKeySource,
            dispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        // AND GIVEN a consumer (reading into a list)
        val emittedValues = mutableListOf<InputEvent>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputEventDataSource.inputEvent.toList(emittedValues)
        }

        // WHEN there is an event emitted
        inputKeyRegistrar.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)

        // THEN it contains the pressed key plus a current time stamp
        assertEquals(2, emittedValues.size)
        val eventTimeInSeconds = emittedValues[0].date.time.div(1000.0)
        val nowInSeconds = Date().time.div(1000.0)
        assertEquals(nowInSeconds, eventTimeInSeconds, 0.05)
    }

}
