package dev.upaya.shf.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.data.auth.LogInStatus
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.settings.composables.ControllerSettingsEntry
import dev.upaya.shf.ui.settings.composables.PacingSettingsEntry
import dev.upaya.shf.ui.settings.composables.UserSettingParams
import dev.upaya.shf.ui.settings.composables.UserSettingsEntry
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SettingsScreen(
    onBackButtonClick: () -> Unit,
    userSettingParams: UserSettingParams,
    onControllerSetupEntryClick: () -> Unit,
    isPacingEnabled: Boolean,
    onSwitchPacing: (Boolean) -> Unit,
    onClickPacingSetting: () -> Unit,
) {

    SetStatusBarColor(
        color = MaterialTheme.colors.secondaryVariant
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings", color = Color.Black)
                },
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "back"
                        )
                    }
                },
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
        ) {

            if (userSettingParams.toggleLogInEnabled) {

                UserSettingsEntry(
                    logInStatus = userSettingParams.logInStatus,
                    emailAddress = userSettingParams.emailAddress,
                    onLogInClick = userSettingParams.onLogInClick,
                    onLogOutClick = userSettingParams.onLogOutClick,
                )

                Divider()

            }

            PacingSettingsEntry(
                isPacingEnabled = isPacingEnabled,
                onSwitchPacing = onSwitchPacing,
                onClick = onClickPacingSetting,
            )

            Divider()

            ControllerSettingsEntry(
                onControllerSetupEntryClick = onControllerSetupEntryClick,
            )

            Divider()

        }

    }

}


@Preview
@Composable
fun SettingsScreenPreview() {

    val userSettingParams = UserSettingParams(
        logInStatus = LogInStatus.LOGGED_OUT,
        emailAddress = null,
        onLogInClick = {},
        onLogOutClick = {},
        toggleLogInEnabled = true,
    )

    SHFTheme(darkTheme = true) {
        SettingsScreen(
            onBackButtonClick = {},
            userSettingParams = userSettingParams,
            onControllerSetupEntryClick = {},
            isPacingEnabled = true,
            onSwitchPacing = {},
            onClickPacingSetting = {},
        )
    }
}
