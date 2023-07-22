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
import dev.upaya.shf.background.AccessibilitySettingSource
import dev.upaya.shf.background.BackgroundNotificationServiceConnection
import dev.upaya.shf.background.startNotificationService
import dev.upaya.shf.background.stopNotificationService
import dev.upaya.shf.inputs.ForegroundInputKeySource
import dev.upaya.shf.inputs.InputKeySource
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject lateinit var inputKeySource: InputKeySource
    @Inject lateinit var foregroundKeyRegistrar: ForegroundInputKeySource
    @Inject lateinit var accessibilitySettingSource: AccessibilitySettingSource

    private val notificationServiceConnection = BackgroundNotificationServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SHFApp(
                onSessionStart = {
                    accessibilitySettingSource.updateAvailability()
                    startNotificationService()
                },
                onSessionStop = { stopNotificationService() },
                onToggleBackgroundSession = { },
            )
        }
        openNotificationSettingsIfNecessary()
        showAccessibilitySettingsIfNecessary()
    }

    override fun onStart() {
        super.onStart()
        notificationServiceConnection.bindTo(this)
    }

    override fun onStop() {
        super.onStop()
        notificationServiceConnection.unbindFrom(this)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyCode == KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event)

        if (foregroundKeyRegistrar.registerKeyDown(keyCode))
            return true

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key released: %s", KeyEvent.keyCodeToString(keyCode))

        if (foregroundKeyRegistrar.registerKeyUp(keyCode))
            return true

        return super.onKeyUp(keyCode, event)
    }

    fun setKeepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    fun clearKeepScreenOn() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun areNotificationsEnabled(): Boolean {
        return NotificationManagerCompat
            .from(this)
            .areNotificationsEnabled()
    }

    private fun openNotificationSettingsIfNecessary() {
        if (!areNotificationsEnabled())
            openNotificationSettings()
    }

    private fun openNotificationSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        }
        startActivity(intent)
    }

    private fun isAccessibilityServiceActive(): Boolean {
        return Settings.Secure.getInt(this.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED) == 1
    }

    private fun showAccessibilitySettingsIfNecessary() {
        if (!isAccessibilityServiceActive())
            showAccessibilitySettings()
    }

    private fun showAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }
}


@Composable
fun SHFApp(
    onSessionStart: () -> Unit,
    onSessionStop: () -> Unit,
    onToggleBackgroundSession: (Boolean) -> Unit,
) {
    SHFTheme(darkTheme = true) {
        SHFNavHost(
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
            onToggleBackgroundSession = onToggleBackgroundSession,
        )
    }
}
