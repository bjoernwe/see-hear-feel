package dev.upaya.shf.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat


class NotificationPermission {

    companion object {

        fun requestNotificationPermissionIfNecessary(context: Context, activityResultLauncher: ActivityResultLauncher<String>) {
            if (!areNotificationsEnabled(context = context))
                activityResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        private fun areNotificationsEnabled(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }

    }

}
