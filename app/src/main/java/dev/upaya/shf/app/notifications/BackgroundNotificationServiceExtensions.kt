package dev.upaya.shf.app.notifications

import android.content.Intent
import dev.upaya.shf.app.SHFActivity


fun SHFActivity.startBackgroundNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    startForegroundService(notificationServiceIntent)
}


fun SHFActivity.stopBackgroundNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    stopService(notificationServiceIntent)
}
