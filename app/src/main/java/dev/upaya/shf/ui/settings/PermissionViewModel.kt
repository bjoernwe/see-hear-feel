package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.accessibility.AccessibilityPermissionSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class PermissionViewModel @Inject constructor(
    accessibilityPermissionSource: AccessibilityPermissionSource,
) : ViewModel() {

    val hasAccessibilityPermission: StateFlow<Boolean> = accessibilityPermissionSource.isEnabled

}
