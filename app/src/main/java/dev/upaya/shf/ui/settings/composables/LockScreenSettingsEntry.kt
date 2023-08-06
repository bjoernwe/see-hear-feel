package dev.upaya.shf.ui.settings.composables

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
internal fun LockScreenSettingsEntry(
    onRequestAccessibilitySettings: () -> Unit,
    hasAccessibilityPermission: Boolean,
    isLockScreenPreferred: Boolean,
    onSwitchLockScreenSession: (Boolean) -> Unit
) {

    SettingsEntry(
        settingsEntryIcon = {
            SettingsEntryIcon(id = R.drawable.baseline_screen_lock_portrait_24)
        },
        primaryText = "Session during locked screen",
        secondaryText = if (!hasAccessibilityPermission) "Click to enable accessibility service in system settings" else null,
        onTextClick = onRequestAccessibilitySettings,
        settingsEntryOption = {
            Switch(
                checked = hasAccessibilityPermission && isLockScreenPreferred,
                enabled = hasAccessibilityPermission,
                onCheckedChange = onSwitchLockScreenSession,
            )
        }
    )

}


@Preview
@Composable
fun LockScreenSettingsEntryPreview() {
    SHFTheme(darkTheme = true) {
        LockScreenSettingsEntry(
            onRequestAccessibilitySettings = {},
            hasAccessibilityPermission = false,
            isLockScreenPreferred = true,
            onSwitchLockScreenSession = {},
        )
    }
}
