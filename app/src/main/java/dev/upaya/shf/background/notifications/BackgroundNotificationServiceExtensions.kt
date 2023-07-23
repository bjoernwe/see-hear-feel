package dev.upaya.shf.background.notifications

import android.app.Activity
import android.content.Intent


fun Activity.startNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    startForegroundService(notificationServiceIntent)
}


fun Activity.stopNotificationService() {
    val notificationServiceIntent = Intent(this, BackgroundNotificationService::class.java)
    stopService(notificationServiceIntent)
}
