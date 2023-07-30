package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.permissions.accessibility.AccessibilityPermissionSource
import dev.upaya.shf.inputs.permissions.notifications.NotificationPermissionSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    notificationPermissionSource: NotificationPermissionSource,
    accessibilityPermissionSource: AccessibilityPermissionSource,
) : ViewModel() {

    val hasNotificationPermission: StateFlow<Boolean> = notificationPermissionSource.hasNotificationPermission
    val hasAccessibilityPermission: StateFlow<Boolean> = accessibilityPermissionSource.isEnabled

    private val _backgroundSessionPermitted = MutableStateFlow(false)
    val backgroundSessionPermitted: StateFlow<Boolean> = _backgroundSessionPermitted

    init {
            CoroutineScope(Dispatchers.Default).launch {
            hasNotificationPermission
                .combine(hasAccessibilityPermission) { x, y -> x.and(y) }
                .collect { _backgroundSessionPermitted.value = it }
        }
    }
}
