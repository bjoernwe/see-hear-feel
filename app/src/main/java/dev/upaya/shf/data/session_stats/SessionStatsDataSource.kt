package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.gamepad.calcDuration
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

    private val labelEvents: MutableList<SHFLabelEvent> = mutableListOf()

    private val _numEvents = MutableStateFlow(0)
    val numEvents: StateFlow<Int> = _numEvents

    private val _sessionLength = MutableStateFlow(0)
    val sessionLength: StateFlow<Int> = _sessionLength

    fun reset() {
        labelEvents.clear()
        updateFlows()
    }

    fun addInputEvent(shfLabelEvent: SHFLabelEvent) {
        labelEvents.add(shfLabelEvent)
        updateFlows()
    }

    private fun updateFlows() {
        _numEvents.value = labelEvents.size
        _sessionLength.value = labelEvents.calcDuration()
    }

    suspend fun calcStats(): SessionStats {
        return withContext(defaultDispatcher) {
            SessionStats.fromLabelEvents(labelEvents)
        }
    }

}
