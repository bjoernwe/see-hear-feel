package dev.upaya.shf.ui.session.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.sources.SessionStats
import dev.upaya.shf.data.sources.SessionStatsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Note, the initialization of this view model is somewhat expensive as it calculates the stats. It
 * is intended to be only used by the stats screen.
 */
@HiltViewModel
class SessionStatsViewModel @Inject constructor(
    sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {

    private val _sessionStats = MutableStateFlow<SessionStats?>(null)
    val sessionStats: StateFlow<SessionStats?> = _sessionStats

    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents
    val sessionLength: StateFlow<Int> = sessionStatsRepository.sessionLength

    init {
        viewModelScope.launch {
            _sessionStats.value = sessionStatsRepository.calcStats()
        }
    }

}
