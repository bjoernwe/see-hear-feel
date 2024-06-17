package dev.upaya.shf.ui.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
internal fun UserSettingsEntry(
    emailAddress: String?,
    onLogInClick: () -> Unit,
) {

    SettingsEntry(
        settingsEntryIcon = {
            SettingsEntryIcon(id = R.drawable.baseline_person_24)
        },
        primaryText = if (emailAddress != null) "Logged in" else "Log in with Google",
        secondaryText = emailAddress,
        onTextClick = onLogInClick,
    ) {
        // logout
    }
}


@Preview
@Composable
fun UserSettingsEntryPreview() {
    SHFTheme(darkTheme = true) {
        Column {
            UserSettingsEntry(
                emailAddress = null,
                onLogInClick = {},
            )
            UserSettingsEntry(
                emailAddress = "someone@gmail.com",
                onLogInClick = {},
            )
        }
    }
}
