package dev.upaya.shf.data.delay

import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import dev.upaya.shf.data.session_state.SessionState
import dev.upaya.shf.data.session_state.SessionStateDataSource
import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.session_stats.SessionStatsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min


@Singleton
class DelayedInputEventDataSource @Inject constructor(
    private val gamepadKeyEventDataSource: GamepadKeyEventDataSource,
    private val sessionStateDataSource: SessionStateDataSource,
    private val sessionStatsDataSource: SessionStatsDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    fun getDelayedInputEvent(externalScope: CoroutineScope): Flow<DelayedInputEvent> {

        val delayedInputEvent = MutableStateFlow(DelayedInputEvent(0))

        externalScope.launch(defaultDispatcher) {

            while (isActive) {

                delay(10)

                if (sessionStateDataSource.sessionState.value == SessionState.NOT_RUNNING)
                    continue  // no session -> no vibration

                if (sessionStatsDataSource.numEvents.value == 0) {
                    // session started but without initial input
                    if (delayedInputEvent.value.delaysInARow > 0)
                        delayedInputEvent.value = DelayedInputEvent(0)  // reset counter
                    continue
                }

                val now = Instant.now()
                val timeSinceLastInput = now.epochSecond - gamepadKeyEventDataSource.inputKeyDown.value.timestamp.epochSecond
                val timeSinceLastDelayNotification = now.epochSecond - delayedInputEvent.value.timestamp.epochSecond
                val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)

                if (timeSinceLastInteraction >= 5000) {
                    val lastCount = delayedInputEvent.value.delaysInARow
                    delayedInputEvent.value = DelayedInputEvent(delaysInARow = lastCount + 1)
                }
            }
        }

        return delayedInputEvent
    }
}
