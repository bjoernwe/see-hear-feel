package dev.upaya.shf.background.settings

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotificationPermissionSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {

    private val _hasNotificationPermission = MutableStateFlow(areNotificationsEnabled())
    val hasNotificationPermission: StateFlow<Boolean> = _hasNotificationPermission

    fun updatePermission(hasNotificationPermission: Boolean) {
        _hasNotificationPermission.value = hasNotificationPermission
    }

    private fun areNotificationsEnabled(): Boolean {
        return ContextCompat.checkSelfPermission(
            appContext, android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openNotificationSettings(context: Context) {
        val intent = Intent().apply {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        }
        context.startActivity(intent)
    }

}
