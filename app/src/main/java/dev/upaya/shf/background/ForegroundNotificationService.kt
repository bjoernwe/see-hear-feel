package dev.upaya.shf.background

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showForegroundNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showForegroundNotification() {

        createNotificationChannel(channelId = CHANNEL_ID)

        val pendingIntent: PendingIntent = Intent(this, SHFActivity::class.java)
            .let { notificationIntent ->
                PendingIntent.getActivity(
                    this,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification = NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setContentTitle("SHF session running ...")
            .setSmallIcon(R.drawable.ic_round_self_improvement_24)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(channelId: String) {
        val name = "SHF Session"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}
