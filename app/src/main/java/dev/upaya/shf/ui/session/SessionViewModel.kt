package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.labels.SHFLabelDataSource
import dev.upaya.shf.data.labels.SHFLabelEvent
import dev.upaya.shf.data.session_history.NotingEventHistoryRepository
import dev.upaya.shf.data.session_state.SessionStateRepository
import dev.upaya.shf.data.session_stats.SessionStatsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionStateRepository: SessionStateRepository,
    private val sessionStatsRepository: SessionStatsRepository,
    private val notingEventHistoryRepository: NotingEventHistoryRepository,
    shfLabelDataSource: SHFLabelDataSource,
) : ViewModel() {

    internal val labelFlow: Flow<SHFLabelEvent> = shfLabelDataSource.labelFlow
    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    init {
        onSessionStart()
    }

    private fun onSessionStart() {
        sessionStateRepository.startSession()
        sessionStatsRepository.startStatsCollection(scope = viewModelScope)
        notingEventHistoryRepository.startRecordingEvents(scope = viewModelScope)
    }

    private fun onSessionStop() {
        sessionStateRepository.stopSession()
    }

    // Is this reliably called as soon as the session screen is closed? If not,
    // NavController.OnDestinationChangedListener may be an alternative way to trigger the event.
    override fun onCleared() {
        super.onCleared()
        onSessionStop()
    }

}
