package dev.upaya.shf.background

import android.content.Context
import android.provider.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber


class AccessibilitySettingSource constructor(private val appContext: Context) {

    private val _backgroundServiceIsAvailable = MutableStateFlow(false)
    val backgroundServiceAvailability: StateFlow<Boolean> = _backgroundServiceIsAvailable

    fun updateAvailability() {
        try {
            val accessSetting = Settings.Secure.getInt(appContext.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
            val accessEnabled = accessSetting == 1
            _backgroundServiceIsAvailable.value = accessEnabled
        } catch (e: Settings.SettingNotFoundException) {
            Timber.e(e)
        }
    }

}
