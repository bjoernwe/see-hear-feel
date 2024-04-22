package dev.upaya.shf.data.delay

import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.preferences.PreferencesDataStore
import dev.upaya.shf.data.session_data.datastore.SessionDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
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
    private val sessionDataStore: SessionDataStore,
    private val preferencesDataStore: PreferencesDataStore,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    companion object {
        const val DELAY_SECONDS = 7
    }

    fun getInputDelayEvents(scope: CoroutineScope): Flow<InputDelayEvent> {

        val inputDelayEvents = MutableStateFlow(InputDelayEvent(delaysInARow = 0, delayInterval = DELAY_SECONDS))

        scope.launch(defaultDispatcher) {

            if (preferencesDataStore.isPacingEnabled.firstOrNull() != true) {
                return@launch
            }

            while (scope.isActive) {

                if (sessionDataStore.numEventsOfCurrentSession.first() == 0) {
                    // session started but without initial input
                    if (inputDelayEvents.value.delaysInARow > 0)
                        inputDelayEvents.value = InputDelayEvent(delaysInARow = 0, delayInterval = DELAY_SECONDS)  // reset counter
                    continue
                }

                val now = Instant.now()
                val timeSinceLastInput = now.epochSecond - gamepadKeyEventDataSource.inputKeyDown.value.timestamp.epochSecond
                val timeSinceLastDelayNotification = now.epochSecond - inputDelayEvents.value.timestamp.epochSecond
                val timeSinceLastInteraction = min(timeSinceLastInput, timeSinceLastDelayNotification)

                if (timeSinceLastInteraction >= DELAY_SECONDS) {
                    val lastCount = inputDelayEvents.value.delaysInARow
                    inputDelayEvents.value = InputDelayEvent(delaysInARow = lastCount + 1, delayInterval = DELAY_SECONDS)
                }

                delay(10)
            }
        }

        return inputDelayEvents.drop(1)
    }
}
