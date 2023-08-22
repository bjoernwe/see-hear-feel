package dev.upaya.shf.app.utils

import android.content.Intent
import android.provider.Settings
import dev.upaya.shf.app.SHFActivity


internal fun SHFActivity.showAccessibilitySettings() {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    startActivity(intent)
}
