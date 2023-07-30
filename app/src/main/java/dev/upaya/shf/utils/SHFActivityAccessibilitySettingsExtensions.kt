package dev.upaya.shf.utils

import android.content.Intent
import android.provider.Settings
import dev.upaya.shf.SHFActivity


internal fun SHFActivity.showAccessibilitySettingsIfNecessary() {
    if (accessibilityPermissionSource.isEnabled.value)
        return
    showAccessibilitySettings()
}

private fun SHFActivity.showAccessibilitySettings() {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    startActivity(intent)
}
