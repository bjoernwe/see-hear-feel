package dev.upaya.shf

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.background.ForegroundNotificationService
import dev.upaya.shf.inputs.InputKeySource
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject lateinit var inputKeySource: InputKeySource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SHFApp(
                onSessionStart = {
                    if (!areNotificationsEnabled())
                        openNotificationSettings()
                    showSessionNotification()
                },
                onSessionStop = { stopSessionNotification() },
            )
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyCode == KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event)

        if (inputKeySource.registerKeyDown(keyCode))
            return true

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key released: %s", KeyEvent.keyCodeToString(keyCode))

        if (inputKeySource.registerKeyUp(keyCode))
            return true

        return super.onKeyUp(keyCode, event)
    }

    fun setKeepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun clearKeepScreenOn() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun showSessionNotification() {
        val notificationServiceIntent = Intent(this, ForegroundNotificationService::class.java)
        startForegroundService(notificationServiceIntent)
    }

    private fun stopSessionNotification() {
        val notificationServiceIntent = Intent(this, ForegroundNotificationService::class.java)
        stopService(notificationServiceIntent)
    }

    private fun areNotificationsEnabled(): Boolean {
        return NotificationManagerCompat
            .from(this)
            .areNotificationsEnabled()
    }

    private fun openNotificationSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        }
        startActivity(intent)
    }
}


@Composable
fun SHFApp(
    onSessionStart: () -> Unit,
    onSessionStop: () -> Unit,
) {
    SHFTheme(darkTheme = true) {
        SHFNavHost(
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
        )
    }
}
