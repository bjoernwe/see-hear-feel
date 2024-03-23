package dev.upaya.shf.data.user_interaction

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInteractionRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val gamepadKeyEventDataSource: GamepadKeyEventDataSource,
    private val inputDelayEventDataSource: InputDelayEventDataSource,
) {

    companion object {
        private val nudgeVibrationPattern = longArrayOf(0, 10, 160, 10)
        private val nudgeVibrationAmplitude = intArrayOf(0, 255, 0, 255)
        private val vibrationEffect: VibrationEffect = VibrationEffect.createWaveform(
            nudgeVibrationPattern, nudgeVibrationAmplitude, -1
        )
    }

    private var vibrator: Vibrator? = null

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {
        return gamepadKeyEventDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {
        return gamepadKeyEventDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun startVibratorForDelayedInputs(scope: CoroutineScope) {
        vibrator = VibratorFactory.getVibrator(context = appContext)
        scope.launch {
            inputDelayEventDataSource.getInputDelayEvents(this)
                .collect {
                    Timber.i("~~~ vibrate ${it.delaysInARow} ~~~")
                    vibrator?.vibrate(vibrationEffect)
                }
        }
    }
}
