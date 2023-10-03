package dev.upaya.shf.data.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


enum class AppState {
    STARTED, RUNNING, PAUSED, STOPPED
}


@Singleton
class AppStateDataSource @Inject constructor() {

    private val _appState = MutableStateFlow(AppState.STOPPED)
    val appState: StateFlow<AppState> = _appState

    fun registerStartEvent() {
        _appState.value = AppState.STARTED
    }

    fun registerResumeEvent() {
        _appState.value = AppState.RUNNING
    }

    fun registerPauseEvent() {
        _appState.value = AppState.PAUSED
    }

    fun registerStopEvent() {
        _appState.value = AppState.STOPPED
    }

}
