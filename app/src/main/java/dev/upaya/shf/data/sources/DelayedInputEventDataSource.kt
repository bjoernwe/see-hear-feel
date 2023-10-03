package dev.upaya.shf.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min


@Singleton
class DelayedInputEventDataSource @Inject constructor(
    private val inputEventDataSource: InputEventDataSource,
    private val sessionStateDataSource: SessionStateDataSource,
    private val sessionStatsDataSource: SessionStatsDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    fun getDelayedInputEvent(externalScope: CoroutineScope): Flow<IntEvent> {

        val delayedInputEvent = MutableStateFlow(IntEvent(0))

        externalScope.launch(defaultDispatcher) {

            val inputEvent = inputEventDataSource.keyDownEvent.stateIn(scope = this)

            while (isActive) {

                delay(10)

                if (sessionStateDataSource.sessionState.value == SessionState.NOT_RUNNING)
                    continue  // no session -> no vibration

                if (sessionStatsDataSource.numEvents.value == 0) {
                    // session started but without initial input
                    if (delayedInputEvent.value.value > 0)
                        delayedInputEvent.value = IntEvent(0)  // reset counter
                    continue
                }

                val now = Date()
                val timeSinceLastInput = now.time - inputEvent.value.date.time
                val timeSinceLastDelayNotification = now.time - delayedInputEvent.value.date.time
                val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)

                if (timeSinceLastInteraction >= 5000) {
                    val lastCount = delayedInputEvent.value.value
                    delayedInputEvent.value = IntEvent(value = lastCount + 1)
                }
            }
        }

        return delayedInputEvent
    }
}
