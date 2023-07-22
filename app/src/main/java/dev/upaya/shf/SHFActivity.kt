package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.background.settings.AccessibilitySettingSource
import dev.upaya.shf.background.notifications.BackgroundNotificationServiceConnection
import dev.upaya.shf.background.notifications.startNotificationService
import dev.upaya.shf.background.notifications.stopNotificationService
import dev.upaya.shf.inputs.ForegroundInputKeySource
import dev.upaya.shf.inputs.InputKeySource
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.utils.AccessibilitySettings
import dev.upaya.shf.utils.NotificationSettings
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
        NotificationSettings.openNotificationSettingsIfNecessary(this)
        AccessibilitySettings.showAccessibilitySettingsIfNecessary(this)
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
