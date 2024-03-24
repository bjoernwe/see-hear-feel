package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.user_interaction.UserInteractionRepository
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_history.SessionHistoryRepository
import dev.upaya.shf.data.session_stats.SessionStatsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionStatsRepository: SessionStatsRepository,
    private val sessionHistoryRepository: SessionHistoryRepository,
    private val userInteractionRepository: UserInteractionRepository,
    shfLabelDataSource: SHFLabelDataSource,
) : ViewModel() {

    internal val labelFlow: Flow<SHFLabelEvent> = shfLabelDataSource.labelFlow
    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    init {
        onSessionStart()
    }

    /*
    This is meant as the authoritative place where a session starts. It's also ended here through
    the viewModelScope, which closes when the user navigates away from the session screen.
     */
    private fun onSessionStart() {
        sessionStatsRepository.startStatsCollection(scope = viewModelScope)
        sessionHistoryRepository.startRecordingEvents(scope = viewModelScope)
        userInteractionRepository.startVibratorForDelayedInputs(scope = viewModelScope)
    }
}
