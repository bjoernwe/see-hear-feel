package dev.upaya.shf.inputs.input_keys

import dev.upaya.shf.inputs.DefaultDispatcher
import dev.upaya.shf.background.settings.AccessibilitySettingSource
import dev.upaya.shf.ui.asSharedFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
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
    accessibilitySettingSource: AccessibilitySettingSource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) : IInputKeySource {

    val usingBackgroundSource: StateFlow<Boolean> = accessibilitySettingSource.backgroundServiceAvailability

    override val inputKeyDown: SharedFlow<InputKey> = merge(
        foregroundInputKeySource.inputKeyDown.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyDown.transform { if (usingBackgroundSource.value) emit(it) },
    ).asSharedFlow(CoroutineScope(dispatcher))

    override val inputKeyUp: SharedFlow<InputKey> = merge(
        foregroundInputKeySource.inputKeyUp.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyUp.transform { if (usingBackgroundSource.value) emit(it) },
    ).asSharedFlow(CoroutineScope(dispatcher))
}
