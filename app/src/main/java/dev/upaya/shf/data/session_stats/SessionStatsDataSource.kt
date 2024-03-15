package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.gamepad_input.GamepadKeyEvent
import dev.upaya.shf.data.gamepad_input.calcSessionLength
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionStatsDataSource @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    private val gamepadKeyEvents: MutableList<GamepadKeyEvent> = mutableListOf()

    private val _numEvents = MutableStateFlow(0)
    val numEvents: StateFlow<Int> = _numEvents

    private val _sessionLength = MutableStateFlow(0)
    val sessionLength: StateFlow<Int> = _sessionLength

    fun reset() {
        gamepadKeyEvents.clear()
        updateFlows()
    }

    fun addInputEvent(gamepadKeyEvent: GamepadKeyEvent) {
        gamepadKeyEvents.add(gamepadKeyEvent)
        updateFlows()
    }

    private fun updateFlows() {
        _numEvents.value = gamepadKeyEvents.size
        _sessionLength.value = gamepadKeyEvents.calcSessionLength()
    }

    suspend fun calcStats(): SessionStats {
        return withContext(defaultDispatcher) {
            SessionStats.fromInputEvents(gamepadKeyEvents)
        }
    }

}
