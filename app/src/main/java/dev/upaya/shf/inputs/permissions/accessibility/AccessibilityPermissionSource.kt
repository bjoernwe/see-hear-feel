package dev.upaya.shf.inputs.permissions.accessibility

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.inputs.permissions.IBooleanSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilityPermissionSource @Inject constructor(
    @ApplicationContext appContext: Context,
) : IBooleanSource {

    private val _backgroundServiceIsAvailable = MutableStateFlow(getAccessibilityServiceAvailability(context = appContext))
    override val isEnabled: StateFlow<Boolean> = _backgroundServiceIsAvailable

    private val accessibilityChangeObserver: ContentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {

        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            updateAvailability(context = appContext)
        }

        private fun updateAvailability(context: Context) {
            _backgroundServiceIsAvailable.value = getAccessibilityServiceAvailability(context = context)
        }

    }

    init {
        registerChangeObserver(context = appContext)
    }

    private fun registerChangeObserver(context: Context) {
        val uri = Settings.Secure.getUriFor(Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        context.contentResolver.registerContentObserver(uri, false, accessibilityChangeObserver)
    }

}
