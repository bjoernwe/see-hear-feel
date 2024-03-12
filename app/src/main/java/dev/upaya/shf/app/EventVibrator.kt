package dev.upaya.shf.app

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import dev.upaya.shf.data.delay.IntEvent
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus


/**
 * @param context Context for querying the vibrator from
 * @param scope Scope for the vibration job to run in
 * @param eventFactory Creates the events on which a vibration happens. Why not pass the flow
 * directly? Because the flow for delayed input events will always be running when its created. But
 * we don't want it running unnecessarily.
 */
class EventVibrator(
    context: Context,
    private val scope: CoroutineScope,
    private val eventFactory: (CoroutineScope) -> Flow<IntEvent>,
) {

    private val vibrator = getVibrator(context)
    private val vibrationEffect: VibrationEffect = VibrationEffect.createWaveform(nudgeVibrationPattern, nudgeVibrationAmplitude, -1)
    private var vibrationJob: CompletableJob? = null

    companion object {

        val nudgeVibrationPattern = longArrayOf(0, 10, 160, 10)
        val nudgeVibrationAmplitude = intArrayOf(0, 255, 0, 255)

        private fun getVibrator(context: Context): Vibrator? {
            return if (Build.VERSION.SDK_INT >= 31) {
                getVibratorNew(context)
            } else {
                getVibratorOld(context)
            }
        }

        @RequiresApi(Build.VERSION_CODES.S)
        private fun getVibratorNew(context: Context): Vibrator? {
            val vibratorManager: VibratorManager
            try {
                vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            } catch (e: NullPointerException) {
                return null
            }
            return vibratorManager.defaultVibrator
        }

        private fun getVibratorOld(context: Context): Vibrator? {
            val vibrator: Vibrator
            try {
                @Suppress("DEPRECATION")
                vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            } catch (e: NullPointerException) {
                return null
            }
            return vibrator
        }
    }

    fun startVibrator() {
        vibrationJob = SupervisorJob().also { job ->
            val vibrationScope = scope + job
            vibrationScope.launch {
                eventFactory(vibrationScope).collect {
                    if (it.value == 0)  // initial event, no actual delay
                        return@collect
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
        vibrator?.vibrate(vibrationEffect)
    }

}
