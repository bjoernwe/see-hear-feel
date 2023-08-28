package dev.upaya.shf.app

import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.data.KeyPressRepository
import dev.upaya.shf.data.sources.NotificationPermissionSource
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.app.utils.NotificationPermission
import dev.upaya.shf.app.utils.showAccessibilitySettings
import dev.upaya.shf.app.utils.startUserInteractionForSession
import dev.upaya.shf.app.utils.stopUserInteractionForSession
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    lateinit var keyPressRepository: KeyPressRepository

    @Inject
    lateinit var notificationPermissionSource: NotificationPermissionSource

    internal lateinit var eventVibrator: EventVibrator

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted: Boolean -> notificationPermissionSource.updatePermission(hasNotificationPermission = isGranted)
        }

        // TODO: Necessary? Only when system dialog is actually used.
        NotificationPermission.requestNotificationPermissionIfNecessary(this, requestPermissionLauncher)

        eventVibrator = EventVibrator(
            events = keyPressRepository.getDelayedInputEvent(scope = lifecycleScope),
            context = this,
            scope = lifecycleScope,
        )

        setContent {
            SHFApp(
                startUserInteractionForSession = ::startUserInteractionForSession,
                stopUserInteractionForSession = ::stopUserInteractionForSession,
                showAccessibilitySettings = ::showAccessibilitySettings
            )
        }

        Settings.Secure.getString(this.contentResolver,  Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyCode == KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event)

        if (keyPressRepository.registerKeyDownFromForeground(keyCode = keyCode))
            return true

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key released: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyPressRepository.registerKeyUpFromForeground(keyCode = keyCode))
            return true

        return super.onKeyUp(keyCode, event)
    }

}


@Composable
fun SHFApp(
    startUserInteractionForSession: (Boolean) -> Unit,
    stopUserInteractionForSession: () -> Unit,
    showAccessibilitySettings: () -> Unit,
) {
    SHFTheme(darkTheme = true) {
        SHFNavHost(
            startUserInteractionForSession = startUserInteractionForSession,
            stopUserInteractionForSession = stopUserInteractionForSession,
            showAccessibilitySettings = showAccessibilitySettings,
        )
    }
}
