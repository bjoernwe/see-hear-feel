package dev.upaya.shf.data.session_stats

import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.gamepad_input.SHFLabelDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val sessionStatsDataSource: SessionStatsDataSource,
    private val shfLabelDataSource: SHFLabelDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    val numEvents: StateFlow<Int> = sessionStatsDataSource.numEvents
    val sessionLength: StateFlow<Int> = sessionStatsDataSource.sessionLength

    suspend fun calcStats(): SessionStats {
        return sessionStatsDataSource.calcStats()
    }

    fun startStatsCollection(scope: CoroutineScope) {

        sessionStatsDataSource.reset()

        scope.launch(defaultDispatcher) {
            shfLabelDataSource.labelFlow.collect { labelEvent ->
                sessionStatsDataSource.addInputEvent(labelEvent)
            }
        }
    }

}
