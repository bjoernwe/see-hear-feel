package dev.upaya.shf.ui.settings.composables

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.data.delay.InputDelayEventDataSource
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
internal fun PacingSettingsEntry(
    isPacingEnabled: Boolean,
    onSwitchPacing: (Boolean) -> Unit
) {

    SettingsEntry(
        settingsEntryIcon = {
            SettingsEntryIcon(id = R.drawable.avg_pace_48px)
        },
        primaryText = "Pacing",
        secondaryText = "Vibrate every ${InputDelayEventDataSource.DELAY_SECONDS}s without input",
    ) {
        Switch(
            checked = isPacingEnabled,
            onCheckedChange = onSwitchPacing,
        )
    }

}


@Preview
@Composable
fun PacingSettingsEntryPreview() {
    SHFTheme(darkTheme = true) {
        PacingSettingsEntry(
            isPacingEnabled = true,
            onSwitchPacing = {},
        )
    }
}
