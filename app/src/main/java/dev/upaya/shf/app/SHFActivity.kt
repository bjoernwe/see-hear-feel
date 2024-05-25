package dev.upaya.shf.app

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.data.preferences.RemoteConfigDataStore
import dev.upaya.shf.data.user_interaction.UserInteractionRepository
import dev.upaya.shf.ui.SHFNavHost
import dev.upaya.shf.ui.theme.SHFTheme
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SHFActivity : ComponentActivity() {

    @Inject
    lateinit var userInteractionRepository: UserInteractionRepository

    @Inject
    lateinit var remoteConfigDataStore: RemoteConfigDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SHFTheme(darkTheme = true) {
                SHFNavHost()
            }
        }
        remoteConfigDataStore.loadRemoteConfig()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.i("Key pressed: %s", KeyEvent.keyCodeToString(keyCode))

        if (keyCode == KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event)

        if (userInteractionRepository.registerKeyDownFromForeground(keyCode = keyCode))
            return true

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        Timber.i("Key released: %s", KeyEvent.keyCodeToString(keyCode))

        if (userInteractionRepository.registerKeyUpFromForeground(keyCode = keyCode))
            return true

        return super.onKeyUp(keyCode, event)
    }

}
