package dev.upaya.shf.app.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.R
import dev.upaya.shf.app.SHFActivity
import dev.upaya.shf.app.EventVibrator
import dev.upaya.shf.data.KeyPressRepository
import javax.inject.Inject


/**
 * Technically speaking, this service is a "Foreground Service" in Android terminology, because it
 * permanently shows a notification. Practically speaking, however, it allows us to display a
 * notification while the app is in background.
 */
@AndroidEntryPoint
class BackgroundNotificationService : LifecycleService() {

    companion object {
        private const val CHANNEL_ID = "SHF_FOREGROUND_NOTIFICATION_SERVICE"
        private const val ONGOING_NOTIFICATION_ID = 1  // Can't be 0
    }

    @Inject
    lateinit var keyPressRepository: KeyPressRepository

    private lateinit var eventVibrator: EventVibrator

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showForegroundNotification()
        eventVibrator = createVibrator()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showForegroundNotification() {
        registerNotificationChannel()
        val notification = createNotification()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createVibrator(): EventVibrator {
        return EventVibrator(
            events = keyPressRepository.getDelayedInputEvent(lifecycleScope),
            context = this,
            scope = lifecycleScope,
        ).apply { startVibrator() }
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
            .setContentTitle("SHF on lock screen")
            .setContentText("Session continues")
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

    override fun onDestroy() {
        eventVibrator.stopVibrator()
        super.onDestroy()
    }
}
