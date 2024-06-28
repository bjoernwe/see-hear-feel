package dev.upaya.shf.ui.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.data.auth.LogInStatus
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
internal fun UserSettingsEntry(
    logInStatus: LogInStatus,
    emailAddress: String?,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
) {

    val doNothing: () -> Unit = {}

    SettingsEntry(
        settingsEntryIcon = {
            when (logInStatus) {
                LogInStatus.LOGGED_IN -> SettingsEntryIcon(id = R.drawable.baseline_person_24)
                LogInStatus.LOGGED_OUT -> SettingsEntryIcon(id = R.drawable.baseline_login_24)
                LogInStatus.LOGGING_IN -> CircularProgressIndicator(color = MaterialTheme.colors.secondaryVariant)
                LogInStatus.LOGGING_OUT -> CircularProgressIndicator(color = MaterialTheme.colors.secondaryVariant)
            }
        },
        primaryText = when (logInStatus) {
            LogInStatus.LOGGED_IN -> "Logged in"
            LogInStatus.LOGGED_OUT -> "Log in with Google"
            LogInStatus.LOGGING_IN -> "Logging in ..."
            LogInStatus.LOGGING_OUT -> "Logging out ..."
        },
        secondaryText = emailAddress,
        onClick = when (logInStatus) {
            LogInStatus.LOGGED_OUT -> onLogInClick
            else -> doNothing
        },
    ) {
        if (logInStatus == LogInStatus.LOGGED_IN) {
            IconButton(onClick = onLogOutClick) {
                SettingsEntryIcon(
                    id = R.drawable.baseline_logout_24,
                    modifier = Modifier
                        .size(32.dp)
                )
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
                logInStatus = LogInStatus.LOGGED_OUT,
                emailAddress = null,
                onLogInClick = {},
                onLogOutClick = {},
            )
            UserSettingsEntry(
                logInStatus = LogInStatus.LOGGED_IN,
                emailAddress = "someone@gmail.com",
                onLogInClick = {},
                onLogOutClick = {},
            )
        }
    }
}
