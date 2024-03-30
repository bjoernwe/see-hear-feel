package dev.upaya.shf.data.user_interaction

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInteractionRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val gamepadKeyEventDataSource: GamepadKeyEventDataSource,
) {

    companion object {
        private val nudgeVibrationPattern = longArrayOf(0, 10, 160, 10)
        private val nudgeVibrationAmplitude = intArrayOf(0, 255, 0, 255)
        private val vibrationEffect: VibrationEffect = VibrationEffect.createWaveform(
            nudgeVibrationPattern, nudgeVibrationAmplitude, -1
        )
    }

    private var vibrator: Vibrator? = VibratorFactory.getVibrator(context = appContext)

    fun registerKeyDownFromForeground(keyCode: Int): Boolean {
        return gamepadKeyEventDataSource.registerKeyDown(keyCode = keyCode)
    }

    fun registerKeyUpFromForeground(keyCode: Int): Boolean {
        return gamepadKeyEventDataSource.registerKeyUp(keyCode = keyCode)
    }

    fun vibrate() {
        Timber.i("~~~ vibrate ~~~")
        vibrator?.vibrate(vibrationEffect)
    }
}
