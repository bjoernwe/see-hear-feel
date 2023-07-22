package dev.upaya.shf.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings


class AccessibilitySettings {

    companion object {

        fun showAccessibilitySettingsIfNecessary(context: Context) {
            if (!isAccessibilityServiceActive(context = context))
                showAccessibilitySettings(context = context)
        }

        private fun isAccessibilityServiceActive(context: Context): Boolean {
            return Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED) == 1
        }

        private fun showAccessibilitySettings(context: Context) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            context.startActivity(intent)
        }

    }
}
