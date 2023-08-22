package dev.upaya.shf.app.notifications

import android.app.Activity
import android.content.Intent


fun Activity.startBackgroundNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    startForegroundService(notificationServiceIntent)
}


fun Activity.stopBackgroundNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    stopService(notificationServiceIntent)
}
