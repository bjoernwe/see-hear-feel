package dev.upaya.shf.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat


class NotificationSettings {

    companion object {

        fun openNotificationSettingsIfNecessary(context: Context) {
            if (!areNotificationsEnabled(context = context))
                openNotificationSettings(context = context)
        }

        private fun areNotificationsEnabled(context: Context): Boolean {
            return NotificationManagerCompat
                .from(context)
                .areNotificationsEnabled()
        }

        private fun openNotificationSettings(context: Context) {
            val intent = Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            context.startActivity(intent)
        }

    }

}
