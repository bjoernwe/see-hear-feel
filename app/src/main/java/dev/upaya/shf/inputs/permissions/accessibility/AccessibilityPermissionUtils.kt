package dev.upaya.shf.inputs.permissions.accessibility

import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import dev.upaya.shf.background.BackgroundKeyEventService
import timber.log.Timber


/**
 * Return whether or not the accessibility service is currently active.
 */
internal fun getAccessibilityServiceAvailability(context: Context): Boolean {
    val accessibilityServiceComponentName = ComponentName(context, BackgroundKeyEventService::class.java)
    return getEnabledAccessibilityServiceComponents(context).contains(accessibilityServiceComponentName)
}


/**
 * Return a list of component names for all the accessibility services that are currently
 * enabled.
 */
private fun getEnabledAccessibilityServiceComponents(context: Context): List<ComponentName> {
    val serviceString = getEnabledAccessibilityServices(context = context)
    return serviceString
        .split(":")
        .mapNotNull(ComponentName::unflattenFromString)
}


/**
 * Return a string from Android settings that lists all enabled accessibility services.
 */
private fun getEnabledAccessibilityServices(context: Context): String {
    try {
        return Settings.Secure.getString(context.contentResolver,  Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
    } catch (e: Settings.SettingNotFoundException) {
        Timber.e(e)
    }
    return ""
}
