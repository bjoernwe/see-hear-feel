package dev.upaya.shf.data.delay

import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.preferences.PreferencesDataStore
import dev.upaya.shf.data.session_stats.SessionStatsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min


@Singleton
class InputDelayEventDataSource @Inject constructor(
    private val gamepadKeyEventDataSource: GamepadKeyEventDataSource,
    private val sessionStatsDataSource: SessionStatsDataSource,
    private val preferencesDataStore: PreferencesDataStore,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    companion object {
        const val DELAY_SECONDS = 7;
    }

    fun getInputDelayEvents(scope: CoroutineScope): Flow<InputDelayEvent> {

        val inputDelayEvents = MutableStateFlow(InputDelayEvent(0))

        scope.launch(defaultDispatcher) {

            if (preferencesDataStore.isPacingEnabled.firstOrNull() != true) {
                return@launch
            }

            while (scope.isActive) {

                delay(10)

                if (sessionStatsDataSource.numEvents.value == 0) {
                    // session started but without initial input
                    if (inputDelayEvents.value.delaysInARow > 0)
                        inputDelayEvents.value = InputDelayEvent(0)  // reset counter
                    continue
                }

                val now = Instant.now()
                val timeSinceLastInput = now.epochSecond - gamepadKeyEventDataSource.inputKeyDown.value.timestamp.epochSecond
                val timeSinceLastDelayNotification = now.epochSecond - inputDelayEvents.value.timestamp.epochSecond
                val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)

                if (timeSinceLastInteraction >= DELAY_SECONDS) {
                    val lastCount = inputDelayEvents.value.delaysInARow
                    inputDelayEvents.value = InputDelayEvent(delaysInARow = lastCount + 1)
                }
            }
        }

        return inputDelayEvents.drop(1)
    }
}
