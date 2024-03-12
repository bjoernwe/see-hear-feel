package dev.upaya.shf.data.stats

import dev.upaya.shf.data.DefaultDispatcher
import dev.upaya.shf.data.input.KeyPressDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject


class SessionStatsRepository @Inject constructor(
    private val sessionStatsDataSource: SessionStatsDataSource,
    private val keyPressDataSource: KeyPressDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    val numEvents: StateFlow<Int> = sessionStatsDataSource.numEvents
    val sessionLength: StateFlow<Int> = sessionStatsDataSource.sessionLength

    suspend fun calcStats(): SessionStats {
        return sessionStatsDataSource.calcStats()
    }

    fun startStatsCollection(scope: CoroutineScope) {

        // Drop current state of StateFlow
        val eventFlow = keyPressDataSource.inputKeyDown.drop(1)

        sessionStatsDataSource.reset()

        scope.launch(defaultDispatcher) {
            eventFlow.collect { inputEvent ->
                sessionStatsDataSource.addInputEvent(inputEvent)
            }
        }
    }

}
