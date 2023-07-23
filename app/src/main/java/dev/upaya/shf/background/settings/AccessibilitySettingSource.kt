package dev.upaya.shf.background.settings

import android.content.Context
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.inputs.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilitySettingSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : IBooleanSource {

    private val _backgroundServiceIsAvailable = MutableStateFlow(false)
    override val value: StateFlow<Boolean> = _backgroundServiceIsAvailable

    init {
        CoroutineScope(dispatcher).launch {
            while (isActive) {
                updateAvailability()
                delay(1000)
            }
        }
    }

    private fun updateAvailability() {
            _backgroundServiceIsAvailable.value = getAccessibilityServiceAvailability()
    }

    private fun getAccessibilityServiceAvailability(): Boolean {
        try {
            return Settings.Secure.getInt(appContext.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED) == 1
        } catch (e: Settings.SettingNotFoundException) {
            Timber.e(e)
        }
        return false
    }

}
