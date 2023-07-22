package dev.upaya.shf.inputs

import android.view.KeyEvent
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
        val foregroundInputKeySource = ForegroundInputKeySource()
        val backgroundInputKeySource = BackgroundInputKeySource()
        val inputKeySource = InputKeySource(
            foregroundInputKeySource = foregroundInputKeySource,
            backgroundInputKeySource = backgroundInputKeySource,
            dispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        // AND GIVEN its emitted values
        val emittedValues = mutableListOf<InputKey>()
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        backgroundScope.launch(dispatcher) {
            inputKeySource.inputKeyDown.toList(emittedValues)
        }

        // WHEN foreground/background keys are received while listening to foreground
        inputKeySource.switchToForeground()
        foregroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_A)  // keep
        backgroundInputKeySource.registerKeyDown(KeyEvent.KEYCODE_BUTTON_B)  // ignore

        // AND WHEN foreground/background keys are received while listening to background
        inputKeySource.switchToBackground()
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