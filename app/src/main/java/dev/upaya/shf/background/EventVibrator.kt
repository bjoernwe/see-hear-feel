package dev.upaya.shf.background

import android.app.Service
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.util.Date


class EventVibrator(
    private val events: SharedFlow<Date>,
    context: Context,
    private val scope: CoroutineScope,
) {

    private val vibrator = getVibrator(context)
    private val vibrationEffect: VibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
    private var vibrationJob: CompletableJob? = null

    companion object {

        private fun getVibrator(context: Context): Vibrator {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
            }
        }
    }

    fun startVibrator() {
        vibrationJob = SupervisorJob().also { job ->
            (scope + job).launch {
                events.collect {
                    vibrate()
                    delay(100)
                    vibrate()
                    delay(100)
                    vibrate()
                }
            }
        }
    }

    fun stopVibrator() {
        vibrationJob?.cancel()
        vibrationJob = null
    }

    private fun vibrate() {
        vibrator.vibrate(vibrationEffect)
    }

}
