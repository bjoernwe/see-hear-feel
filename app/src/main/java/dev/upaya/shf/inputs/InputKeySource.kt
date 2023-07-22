package dev.upaya.shf.inputs

import dev.upaya.shf.DefaultDispatcher
import dev.upaya.shf.ui.asSharedFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InputKeySource @Inject constructor(
    foregroundInputKeySource: ForegroundInputKeySource,
    backgroundInputKeySource: BackgroundInputKeySource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) : IInputKeySource {

    private val _usingBackgroundSource = MutableStateFlow(false)
    val usingBackgroundSource: StateFlow<Boolean> = _usingBackgroundSource

    override val inputKeyDown: SharedFlow<InputKey> = merge(
        foregroundInputKeySource.inputKeyDown.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyDown.transform { if (usingBackgroundSource.value) emit(it) },
    ).asSharedFlow(CoroutineScope(dispatcher))

    override val inputKeyUp: SharedFlow<InputKey> = merge(
        foregroundInputKeySource.inputKeyUp.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyUp.transform { if (usingBackgroundSource.value) emit(it) },
    ).asSharedFlow(CoroutineScope(dispatcher))

    fun switchToForeground() {
        _usingBackgroundSource.value = false
    }

    fun switchToBackground() {
        _usingBackgroundSource.value = true
    }
}
