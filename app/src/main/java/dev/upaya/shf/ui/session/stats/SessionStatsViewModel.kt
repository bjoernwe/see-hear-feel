package dev.upaya.shf.ui.session.stats

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.SessionStatsRepository
import dev.upaya.shf.data.session_data.dataclasses.AllTimeStats
import dev.upaya.shf.data.session_data.dataclasses.SessionStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


/**
 * Note, the initialization of this view model is somewhat expensive as it calculates the stats. It
 * is intended to be only used by the stats screen.
 */
@HiltViewModel
class SessionStatsViewModel @Inject constructor(
    sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {
    val labelFrequencies: Flow<Map<SHFLabel, Int>> = sessionStatsRepository.labelFreqs
    val accumulatedNotingsPerDay = sessionStatsRepository.accumulatedNotingsPerDay

    val sessionStats: Flow<SessionStats> = combine(
        sessionStatsRepository.numEvents,
        sessionStatsRepository.sessionDurationSeconds,
        sessionStatsRepository.amountMindWandering,
    ) { numberOfNotings, sessionDurationSeconds, amountMindWandering ->
        SessionStats(
            numberOfNotings = numberOfNotings,
            sessionDurationSeconds = sessionDurationSeconds,
            amountMindWandering = amountMindWandering,
        )
    }

    val allTimeStats: Flow<AllTimeStats> = combine(
        sessionStatsRepository.numEventsInDB,
        sessionStatsRepository.numOfSessions,
        sessionStatsRepository.numOfDays,
    ) { numEvents, numSessions, numDays ->
        AllTimeStats(
            numNotings = numEvents,
            numSessions = numSessions,
            numDays = numDays,
        )
    }
}
