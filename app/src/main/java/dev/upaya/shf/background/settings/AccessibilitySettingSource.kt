package dev.upaya.shf.background.settings

import android.content.ComponentName
import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.background.BackgroundKeyEventService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilitySettingSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : IBooleanSource {

    private val _backgroundServiceIsAvailable = MutableStateFlow(getAccessibilityServiceAvailability())
    override val isEnabled: StateFlow<Boolean> = _backgroundServiceIsAvailable

    private val accessibilityChangeObserver: ContentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            updateAvailability()
        }
    }

    init {
        registerChangeObserver()
    }

    private fun registerChangeObserver() {
        val uri = Settings.Secure.getUriFor(Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        appContext.contentResolver.registerContentObserver(uri, false, accessibilityChangeObserver)
    }

    private fun updateAvailability() {
            _backgroundServiceIsAvailable.value = getAccessibilityServiceAvailability()
    }

    /**
     * Return whether or not the accessibility service is currently active.
     */
    private fun getAccessibilityServiceAvailability(): Boolean {
        val accessibilityServiceComponentName = ComponentName(appContext, BackgroundKeyEventService::class.java)
        return getEnabledAccessibilityServiceComponents().contains(accessibilityServiceComponentName)
    }

    /**
     * Return a list of component names for all the accessibility services that are currently
     * enabled.
     */
    private fun getEnabledAccessibilityServiceComponents(): List<ComponentName> {
        val serviceString = getEnabledAccessibilityServices()
        return serviceString
            .split(":")
            .mapNotNull(ComponentName::unflattenFromString)
    }

    /**
     * Return a string from Android settings that lists all enabled accessibility services.
     */
    private fun getEnabledAccessibilityServices(): String {
        try {
            return Settings.Secure.getString(appContext.contentResolver,  Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        } catch (e: Settings.SettingNotFoundException) {
            Timber.e(e)
        }
        return ""
    }

}
