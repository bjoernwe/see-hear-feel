package dev.upaya.shf

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.background.EventVibrator
import dev.upaya.shf.background.notifications.startBackgroundNotificationService
import dev.upaya.shf.background.notifications.stopBackgroundNotificationService
import dev.upaya.shf.background.settings.AccessibilitySettingSource
import dev.upaya.shf.background.settings.NotificationPermissionSource
import dev.upaya.shf.inputs.DelayedInputEventSource
import dev.upaya.shf.inputs.input_keys.ForegroundKeySource
import dev.upaya.shf.inputs.input_keys.IInputKeyRegistrar
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.utils.AccessibilitySettings
import dev.upaya.shf.utils.NotificationPermission
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    @ForegroundKeySource lateinit var foregroundKeyRegistrar: IInputKeyRegistrar

    @Inject
    lateinit var accessibilitySettingSource: AccessibilitySettingSource

    @Inject
    lateinit var notificationPermissionSource: NotificationPermissionSource

    @Inject
    lateinit var delayedInputEventSource: DelayedInputEventSource
    private lateinit var eventVibrator: EventVibrator

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted: Boolean -> notificationPermissionSource.updatePermission(hasNotificationPermission = isGranted)
        }

        NotificationPermission.requestNotificationPermissionIfNecessary(this, requestPermissionLauncher)
        AccessibilitySettings.showAccessibilitySettingsIfNecessary(this)

        eventVibrator = EventVibrator(
            events = delayedInputEventSource.delayedInputEvent,
            context = this,
            scope = lifecycleScope,
        )

        setContent {
            SHFApp(
                onSessionStart = ::startUserInteractionForSession,
                onSessionStop = ::stopUserInteractionForSession,
            )
        }

    }

    /**
     * Start the user interaction that's happening during a session. In particular the parts that
     * are kept out of other parts of the architecture because they depend on Android libraries
     * and/or lifecycle-dependent context. Like foreground-service notifications and vibrations.
     */
    private fun startUserInteractionForSession() {
        if (accessibilitySettingSource.value.value)
            startBackgroundNotificationService()
        else
            eventVibrator.startVibrator()
    }

    private fun stopUserInteractionForSession() {
        stopBackgroundNotificationService()
        eventVibrator.stopVibrator()
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
) {
    SHFTheme(darkTheme = true) {
        SHFNavHost(
            onSessionStart = onSessionStart,
            onSessionStop = onSessionStop,
        )
    }
}
