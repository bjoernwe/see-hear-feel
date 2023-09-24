package dev.upaya.shf.app.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat


class NotificationPermission {

    companion object {

        fun requestNotificationPermissionIfNecessary(context: Context, activityResultLauncher: ActivityResultLauncher<String>) {
            if (Build.VERSION.SDK_INT < 33)
                return
            if (!areNotificationsEnabled(context = context))
                activityResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        private fun areNotificationsEnabled(context: Context): Boolean {
            if (Build.VERSION.SDK_INT < 33)
                return true
            return ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }

    }

}
