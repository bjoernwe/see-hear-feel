package dev.upaya.shf.background.settings

import android.content.Context
import android.provider.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilitySettingSource @Inject constructor() : IBooleanSource {

    private val _backgroundServiceIsAvailable = MutableStateFlow(false)
    override val value: StateFlow<Boolean> = _backgroundServiceIsAvailable

    fun updateAvailability(context: Context) {
            _backgroundServiceIsAvailable.value = getAccessibilityServiceAvailability(context = context)
    }

    private fun getAccessibilityServiceAvailability(context: Context): Boolean {
        try {
            return Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED) == 1
        } catch (e: Settings.SettingNotFoundException) {
            Timber.e(e)
        }
        return false
    }

}
