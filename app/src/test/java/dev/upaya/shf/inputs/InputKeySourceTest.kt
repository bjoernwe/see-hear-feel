package dev.upaya.shf.inputs

import android.view.KeyEvent
import dev.upaya.shf.background.settings.MockBooleanSource
import dev.upaya.shf.inputs.input_keys.InputKey
import dev.upaya.shf.inputs.input_keys.InputKeyMapping
import dev.upaya.shf.inputs.input_keys.GlobalInputKeySource
import dev.upaya.shf.inputs.input_keys.InputKeyRegistrar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


class InputKeySourceTest {

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun switchBackground_emittedKeys_areFilteredForBackground() = runTest {

        // GIVEN a InputKeySource (foreground/background)
        val accessibilitySettingSource = MockBooleanSource(false)
        val foregroundInputKeySource = InputKeyRegistrar().apply { this.enableRegistrar() }
        val backgroundInputKeySource = InputKeyRegistrar().apply { this.enableRegistrar() }
        val inputKeySource = GlobalInputKeySource(
            foregroundInputKeySource = foregroundInputKeySource,
            backgroundInputKeySource = backgroundInputKeySource,
            dispatcher = UnconfinedTestDispatcher(testScheduler),
            accessibilitySettingSource = accessibilitySettingSource,
        )

        // AND GIVEN its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeySource.inputKeyDown.toList(emittedValues)
        }

        // WHEN foreground/background keys are received while listening to foreground
        accessibilitySettingSource.setValue(false)
        foregroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)  // keep
        backgroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_B)  // ignore

        // AND WHEN foreground/background keys are received while listening to background
        accessibilitySettingSource.setValue(true)
        foregroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_X)  // ignore
        backgroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_Y)  // keep

        // THEN only two events have been emitted
        val numEmittedValues = emittedValues.size
        Assert.assertEquals(2, numEmittedValues)

        // AND THEN only the appropriate foreground/background events have been transmitted
        val expectedForegroundKey = InputKeyMapping.getInputKey(KeyEvent.KEYCODE_BUTTON_A)
        val expectedBackgroundKey = InputKeyMapping.getInputKey(KeyEvent.KEYCODE_BUTTON_Y)
        Assert.assertEquals(expectedForegroundKey, emittedValues[0])
        Assert.assertEquals(expectedBackgroundKey, emittedValues[1])
    }
}