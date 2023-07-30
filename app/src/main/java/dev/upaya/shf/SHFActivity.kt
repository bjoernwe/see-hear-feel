package dev.upaya.shf

import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.background.EventVibrator
import dev.upaya.shf.inputs.permissions.accessibility.AccessibilityPermissionSource
import dev.upaya.shf.inputs.permissions.notifications.NotificationPermissionSource
import dev.upaya.shf.inputs.DelayedInputEventSource
import dev.upaya.shf.inputs.keys.ForegroundKeySource
import dev.upaya.shf.inputs.keys.IInputKeyRegistrar
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.utils.NotificationPermission
import dev.upaya.shf.utils.showAccessibilitySettingsIfNecessary
import dev.upaya.shf.utils.startUserInteractionForSession
import dev.upaya.shf.utils.stopUserInteractionForSession
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    @ForegroundKeySource lateinit var foregroundKeyRegistrar: IInputKeyRegistrar

    @Inject
    lateinit var accessibilityPermissionSource: AccessibilityPermissionSource

    @Inject
    lateinit var notificationPermissionSource: NotificationPermissionSource

    @Inject
    lateinit var delayedInputEventSource: DelayedInputEventSource
    internal lateinit var eventVibrator: EventVibrator

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted: Boolean -> notificationPermissionSource.updatePermission(hasNotificationPermission = isGranted)
        }

        // TODO: Necessary? Only when system dialog is actually used.
        NotificationPermission.requestNotificationPermissionIfNecessary(this, requestPermissionLauncher)

        eventVibrator = EventVibrator(
            events = delayedInputEventSource.delayedInputEvent,
            context = this,
            scope = lifecycleScope,
        )

        setContent {
            SHFApp(
                onSessionStart = ::startUserInteractionForSession,
                onSessionStop = ::stopUserInteractionForSession,
                showAccessibilitySettings = ::showAccessibilitySettingsIfNecessary
            )
        }

        Settings.Secure.getString(this.contentResolver,  Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)

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

}


@Composable
fun SHFApp(
    onSessionStart: () -> Unit,
    onSessionStop: () -> Unit,
    showAccessibilitySettings: () -> Unit,
) {
    SHFTheme(darkTheme = true) {
        SHFNavHost(
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
            showAccessibilitySettings = showAccessibilitySettings,
        )
    }
}
