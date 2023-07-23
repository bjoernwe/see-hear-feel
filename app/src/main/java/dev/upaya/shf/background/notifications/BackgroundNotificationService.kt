package dev.upaya.shf.background.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.R
import dev.upaya.shf.SHFActivity
import dev.upaya.shf.inputs.input_keys.BackgroundKeySource
import dev.upaya.shf.inputs.input_keys.IInputKeyRegistrar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Technically speaking, this service is a "Foreground Service" in Android terminology, because it
 * permanently shows a notification. Practically speaking, however, it allows us to display a
 * notification while the app is in background.
 */
@AndroidEntryPoint
class BackgroundNotificationService : Service() {

    @Inject
    @BackgroundKeySource
    lateinit var backgroundInputKeySource: IInputKeyRegistrar

    private val CHANNEL_ID = "SHF_FOREGROUND_NOTIFICATION_SERVICE"
    private val ONGOING_NOTIFICATION_ID = 1  // Can't be 0

    private val binder = LocalBinder()  // Allows an Activity to bind to this service

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val vibrationEffect: VibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showForegroundNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showForegroundNotification() {
        registerNotificationChannel()
        val notification = createNotification()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
        scope.launch {
            backgroundInputKeySource.inputKeyDown.collect { vibrate() }
        }
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
            .setContentTitle("SHF running in background")
            .setContentText("Session continues during locked screen")
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
        fun getService(): BackgroundNotificationService = this@BackgroundNotificationService
    }

    override fun onBind(intend: Intent): IBinder {
        return binder
    }

    private fun vibrate() {
        getVibrator().vibrate(vibrationEffect)
    }

    private fun getVibrator(): Vibrator {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
