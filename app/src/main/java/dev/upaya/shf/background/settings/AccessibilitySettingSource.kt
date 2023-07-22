package dev.upaya.shf.background.settings

import android.content.Context
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilitySettingSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {

    private val _backgroundServiceIsAvailable = MutableStateFlow(
        getAccessibilityServiceAvailability()
    )
    val backgroundServiceAvailability: StateFlow<Boolean> = _backgroundServiceIsAvailable

    fun updateAvailability() {
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
