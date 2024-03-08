package dev.upaya.shf.app

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.data.UserInteractionRepository
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    lateinit var userInteractionRepository: UserInteractionRepository

    private lateinit var eventVibrator: EventVibrator

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        eventVibrator = EventVibrator(
            context = this,
            scope = lifecycleScope,
            eventFactory = userInteractionRepository::getDelayedInputEvent,
        )

        setContent {
            SHFApp()
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
fun SHFApp() {
    SHFTheme(darkTheme = true) {
        SHFNavHost()
    }
}
