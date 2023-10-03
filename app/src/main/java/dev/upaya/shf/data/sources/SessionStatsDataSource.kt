package dev.upaya.shf.data.sources

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
    }

    fun addInputEvent(inputEvent: InputEvent) {
        inputEvents.add(inputEvent)
        _numEvents.value = inputEvents.size
        _sessionLength.value = inputEvents.calcSessionLength()
    }

    suspend fun calcStats(): SessionStats {
        return withContext(defaultDispatcher) {
            SessionStats.fromInputEvents(inputEvents)
        }
    }

}
