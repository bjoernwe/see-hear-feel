package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.input.InputEvent
import dev.upaya.shf.data.UserInteractionRepository
import dev.upaya.shf.data.sessionhistory.SessionHistoryRepository
import dev.upaya.shf.data.sessionstate.SessionStateRepository
import dev.upaya.shf.data.stats.SessionStatsRepository
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    userInteractionRepository: UserInteractionRepository,
    private val sessionStateRepository: SessionStateRepository,
    private val sessionStatsRepository: SessionStatsRepository,
    private val sessionHistoryRepository: SessionHistoryRepository,
) : ViewModel() {

    // Drop current state of StateFlow
    internal val inputEventFlow: Flow<InputEvent> = userInteractionRepository.keyDown.drop(1)
    internal val labelFlow: SharedFlow<Label> = userInteractionRepository.keyDown.transformToLabel(scope = viewModelScope)
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
