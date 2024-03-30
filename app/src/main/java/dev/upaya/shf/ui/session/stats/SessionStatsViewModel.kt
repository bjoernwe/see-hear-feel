package dev.upaya.shf.ui.session.stats

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.SessionStatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Note, the initialization of this view model is somewhat expensive as it calculates the stats. It
 * is intended to be only used by the stats screen.
 */
@HiltViewModel
class SessionStatsViewModel @Inject constructor(
    sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {
    val sessionStats: Flow<Map<SHFLabel, Int>> = sessionStatsRepository.labelFreqs
    val allTimeStats = sessionStatsRepository.allTimeStats
    val numEvents: Flow<Int> = sessionStatsRepository.numEvents
    val sessionDurationSeconds: Flow<Long?> = sessionStatsRepository.sessionDurationSeconds
}
