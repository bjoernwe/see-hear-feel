package dev.upaya.shf.ui.settings.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerSettingsEntry(
    onControllerSetupEntryClick: () -> Unit,
) {

    SettingsEntry(
        settingsEntryIcon = {
            SettingsEntryIcon(id = R.drawable.ic_round_videogame_asset_24)
        },
        primaryText = "Controller setup",
        onTextClick = onControllerSetupEntryClick,
    )

}


@Preview
@Composable
fun ControllerSetupEntryPreview() {
    SHFTheme(darkTheme = true) {
        ControllerSettingsEntry(
            onControllerSetupEntryClick = {},
        )
    }
}
