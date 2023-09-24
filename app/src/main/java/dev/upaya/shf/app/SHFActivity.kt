package dev.upaya.shf.app

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.app.utils.NotificationPermission
import dev.upaya.shf.app.utils.showAccessibilitySettings
import dev.upaya.shf.app.utils.startUserInteractionForSession
import dev.upaya.shf.app.utils.stopUserInteractionForSession
import dev.upaya.shf.data.UserInteractionRepository
import dev.upaya.shf.data.sources.NotificationPermissionSource
import dev.upaya.shf.data.sources.SessionStateRepository
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    lateinit var userInteractionRepository: UserInteractionRepository

    @Inject
    lateinit var notificationPermissionSource: NotificationPermissionSource

    @Inject
    lateinit var sessionStateRepository: SessionStateRepository

    internal lateinit var eventVibrator: EventVibrator

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted: Boolean -> notificationPermissionSource.updatePermission(hasNotificationPermission = isGranted)
        }

        // TODO: Necessary? Only when system dialog is actually used.
        NotificationPermission.requestNotificationPermissionIfNecessary(this, requestPermissionLauncher)

        eventVibrator = EventVibrator(
            context = this,
            scope = lifecycleScope,
            eventFactory = userInteractionRepository::getDelayedInputEvent,
        )

        setContent {
            SHFApp(
                startUserInteractionForSession = ::startUserInteractionForSession,
                stopUserInteractionForSession = ::stopUserInteractionForSession,
                showAccessibilitySettings = ::showAccessibilitySettings
            )
        }

        lifecycleScope.launch {
            userInteractionRepository.vibrationFromForeground.collect { enabled ->
                if (enabled)
                    eventVibrator.startVibrator()
                else
                    eventVibrator.stopVibrator()
            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyCode == KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event)

        if (userInteractionRepository.registerKeyDownFromForeground(keyCode = keyCode))
            return true

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.tag("foo").i("Key released: %s", KeyEvent.keyCodeToString(keyCode))

        if (userInteractionRepository.registerKeyUpFromForeground(keyCode = keyCode))
            return true

        return super.onKeyUp(keyCode, event)
    }

    override fun onStart() {
        super.onStart()
        userInteractionRepository.registerAppStartEvent()
    }

    override fun onResume() {
        super.onResume()
        userInteractionRepository.registerAppResumeEvent()
    }

    override fun onPause() {
        super.onPause()
        userInteractionRepository.registerAppPauseEvent()
    }

    override fun onStop() {
        super.onStop()
        userInteractionRepository.registerAppStopEvent()
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
