package dev.upaya.shf.inputs

import dev.upaya.shf.inputs.events.IInputEventSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min


@Singleton
class DelayedInputEventSource @Inject constructor(
    inputEventSource: IInputEventSource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) {

    private val _delayedInputEvent = MutableStateFlow(Date())
    val delayedInputEvent: StateFlow<Date> = _delayedInputEvent

    init {
        CoroutineScope(dispatcher).launch {
            while (isActive) {
                val now = Date()
                val timeSinceLastInput = now.time - inputEventSource.inputEvent.value.date.time
                val timeSinceLastDelayNotification = now.time - delayedInputEvent.value.time
                val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)
                if (timeSinceLastInteraction >= 5000) {
                    _delayedInputEvent.value = now
                }
                delay(100)
            }
        }
    }

}
