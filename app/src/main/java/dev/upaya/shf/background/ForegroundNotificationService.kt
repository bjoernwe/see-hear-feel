package dev.upaya.shf.background

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.R
import dev.upaya.shf.SHFActivity
import dev.upaya.shf.inputs.InputKeySource
import javax.inject.Inject


@AndroidEntryPoint
class ForegroundNotificationService : Service() {

    @Inject
    lateinit var inputKeySource: InputKeySource

    private val CHANNEL_ID = "SHF_FOREGROUND_NOTIFICATION_SERVICE"
    private val ONGOING_NOTIFICATION_ID = 1  // Can't be 0

    private val binder = LocalBinder()  // Allows an Activity to bind to this service

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showForegroundNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showForegroundNotification() {
        registerNotificationChannel()
        val notification = createNotification()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createNotification(): Notification {

        val pendingIntent: PendingIntent = Intent(this, SHFActivity::class.java)
            .let { notificationIntent ->
                PendingIntent.getActivity(
                    this,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        return NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setContentTitle("SHF session running ...")
            .setSmallIcon(R.drawable.ic_round_self_improvement_24)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun registerNotificationChannel() {
        val name = "SHF Session"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods.
        fun getService(): ForegroundNotificationService = this@ForegroundNotificationService
    }

    override fun onBind(intend: Intent): IBinder {
        return binder
    }
}
