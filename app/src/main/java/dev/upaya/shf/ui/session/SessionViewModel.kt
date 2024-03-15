package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.gamepad_input.SHFLabelDataSource
import dev.upaya.shf.data.gamepad_input.SHFLabelEvent
import dev.upaya.shf.data.session_history.SessionHistoryRepository
import dev.upaya.shf.data.session_state.SessionStateRepository
import dev.upaya.shf.data.session_stats.SessionStatsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionStateRepository: SessionStateRepository,
    private val sessionStatsRepository: SessionStatsRepository,
    private val sessionHistoryRepository: SessionHistoryRepository,
    shfLabelDataSource: SHFLabelDataSource,
) : ViewModel() {

    internal val labelFlow: Flow<SHFLabelEvent> = shfLabelDataSource.labelFlow
    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    fun onSessionStart() {
        sessionStateRepository.startSession()
        sessionStatsRepository.startStatsCollection(scope = viewModelScope)
        sessionHistoryRepository.startRecording(scope = viewModelScope)
    }

    internal fun onSessionStop() {
        sessionStateRepository.stopSession()
    }
}
