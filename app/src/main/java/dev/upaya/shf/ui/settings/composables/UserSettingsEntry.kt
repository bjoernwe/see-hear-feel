package dev.upaya.shf.ui.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
internal fun UserSettingsEntry(
    emailAddress: String?,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
) {

    val isLoggedIn = emailAddress != null
    val doNoting: () -> Unit = {}

    SettingsEntry(
        settingsEntryIcon = {
            if (isLoggedIn)
                SettingsEntryIcon(id = R.drawable.baseline_person_24)
            else
                SettingsEntryIcon(id = R.drawable.baseline_login_24)
        },
        primaryText = if (isLoggedIn) "Logged in" else "Log in with Google",
        secondaryText = emailAddress,
        onTextClick = if (isLoggedIn) doNoting else onLogInClick,
    ) {
        if (isLoggedIn) {
            IconButton(onClick = onLogOutClick) {
                SettingsEntryIcon(id = R.drawable.baseline_logout_24)
            }
        }
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
                onLogOutClick = {},
            )
            UserSettingsEntry(
                emailAddress = "someone@gmail.com",
                onLogInClick = {},
                onLogOutClick = {},
            )
        }
    }
}
