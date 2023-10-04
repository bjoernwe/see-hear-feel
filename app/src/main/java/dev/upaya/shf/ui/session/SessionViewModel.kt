package dev.upaya.shf.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.ui.LabelMapSHF
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.data.UserInteractionRepository
import dev.upaya.shf.data.sources.SessionStateRepository
import dev.upaya.shf.data.sources.SessionStatsRepository
import dev.upaya.shf.ui.transformToLabel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    userInteractionRepository: UserInteractionRepository,
    private val sessionStateRepository: SessionStateRepository,
    private val sessionStatsRepository: SessionStatsRepository,
) : ViewModel() {

    internal val inputEventFlow: StateFlow<InputEvent> = userInteractionRepository.keyDown
    
    // TODO: Move labels to repository. Initially they've been kept out they could be seen as part of the presentation layer. But...

    internal val labelFlow: SharedFlow<Label> = userInteractionRepository.keyDown.transformToLabel(labelMap = LabelMapSHF, scope = viewModelScope)
    val numEvents: StateFlow<Int> = sessionStatsRepository.numEvents

    fun startSession() {
        viewModelScope.launch {
            sessionStateRepository.startSession()
            sessionStatsRepository.startStatsCollection(inputEventFlow = inputEventFlow)
        }
    }

    internal fun stopSession() {
        sessionStateRepository.stopSession()
    }
}
