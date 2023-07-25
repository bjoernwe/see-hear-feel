package dev.upaya.shf.background.notifications

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
