package dev.upaya.shf.inputs.keys

import dev.upaya.shf.inputs.permissions.IBooleanSource
import dev.upaya.shf.inputs.permissions.accessibility.AccessibilityPermission
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


/**
 * [GlobalInputKeySource] is a data source that merges together the input streams coming from
 * [ForegroundKeySource] and [BackgroundKeySource].
 */
@Singleton
class GlobalInputKeySource @Inject constructor(
    @ForegroundKeySource foregroundInputKeySource: IInputKeyRegistrar,
    @BackgroundKeySource backgroundInputKeySource: IInputKeyRegistrar,
    @AccessibilityPermission accessibilityPermissionSource: IBooleanSource,
) {

    private val usingBackgroundSource: StateFlow<Boolean> = accessibilityPermissionSource.isEnabled

    val inputKeyDown: Flow<InputKey> = merge(
        foregroundInputKeySource.inputKeyDown.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyDown.transform { if (usingBackgroundSource.value) emit(it) },
    )

    val inputKeyUp: Flow<InputKey> = merge(
        foregroundInputKeySource.inputKeyUp.transform { if (!usingBackgroundSource.value) emit(it) },
        backgroundInputKeySource.inputKeyUp.transform { if (usingBackgroundSource.value) emit(it) },
    )
}
