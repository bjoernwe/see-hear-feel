package dev.upaya.shf.inputs

import dev.upaya.shf.inputs.events.InputEventSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min


@Singleton
class DelayedInputEventSource @Inject constructor(
    private val inputEventSource: InputEventSource,
    private val sessionStateSource: SessionStateSource,
) {

    fun getDelayedInputEvent(scope: CoroutineScope): StateFlow<IntEvent> {

        val delayedInputEvent = MutableStateFlow(IntEvent(0))

        scope.launch {
            val inputEvent = inputEventSource.inputEvent.stateIn(scope = this)
            while (isActive) {
                val lastCount = delayedInputEvent.value.value
                if (sessionStateSource.isSessionRunning.value) {
                    val now = Date()
                    val timeSinceLastInput = now.time - inputEvent.value.date.time
                    val timeSinceLastDelayNotification = now.time - delayedInputEvent.value.date.time
                    val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)
                    if (timeSinceLastInteraction >= 5000) {
                        delayedInputEvent.value = IntEvent(value = lastCount + 1, date = now)
                    }
                } else {
                    if (lastCount > 0)
                        delayedInputEvent.value = IntEvent(0)
                }
                delay(100)
            }
        }

        return delayedInputEvent
    }
}
