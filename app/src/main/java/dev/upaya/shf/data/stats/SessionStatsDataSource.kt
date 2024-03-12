package dev.upaya.shf.data.stats

import dev.upaya.shf.data.sources.DefaultDispatcher
import dev.upaya.shf.data.input.InputEvent
import dev.upaya.shf.data.input.calcSessionLength
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

    private val inputEvents: MutableList<InputEvent> = mutableListOf()

    private val _numEvents = MutableStateFlow(0)
    val numEvents: StateFlow<Int> = _numEvents

    private val _sessionLength = MutableStateFlow(0)
    val sessionLength: StateFlow<Int> = _sessionLength

    fun reset() {
        inputEvents.clear()
        updateFlows()
    }

    fun addInputEvent(inputEvent: InputEvent) {
        inputEvents.add(inputEvent)
        updateFlows()
    }

    private fun updateFlows() {
        _numEvents.value = inputEvents.size
        _sessionLength.value = inputEvents.calcSessionLength()
    }

    suspend fun calcStats(): SessionStats {
        return withContext(defaultDispatcher) {
            SessionStats.fromInputEvents(inputEvents)
        }
    }

}
