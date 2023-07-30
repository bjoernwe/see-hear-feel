package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.permissions.accessibility.AccessibilityPermissionSource
import dev.upaya.shf.inputs.permissions.notifications.NotificationPermissionSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class PermissionViewModel @Inject constructor(
    notificationPermissionSource: NotificationPermissionSource,
    accessibilityPermissionSource: AccessibilityPermissionSource,
) : ViewModel() {

    val hasNotificationPermission: StateFlow<Boolean> = notificationPermissionSource.hasNotificationPermission
    val hasAccessibilityPermission: StateFlow<Boolean> = accessibilityPermissionSource.isEnabled

}
