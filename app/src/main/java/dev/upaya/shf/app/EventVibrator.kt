package dev.upaya.shf.app

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import dev.upaya.shf.data.sources.IntEvent
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
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
    private val vibrationEffect: VibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
    private var vibrationJob: CompletableJob? = null

    companion object {

        private fun getVibrator(context: Context): Vibrator {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            return vibratorManager.defaultVibrator
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
