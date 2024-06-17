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
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.settings.composables.ControllerSettingsEntry
import dev.upaya.shf.ui.settings.composables.PacingSettingsEntry
import dev.upaya.shf.ui.settings.composables.UserSettingsEntry
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SettingsScreen(
    onBackButtonClick: () -> Unit,
    onLogInClick: () -> Unit,
    onControllerSetupEntryClick: () -> Unit,
    userEmailAddress: String?,
    toggleLogInEnabled: Boolean,
    isPacingEnabled: Boolean,
    onSwitchPacing: (Boolean) -> Unit,
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

            if (toggleLogInEnabled) {

                UserSettingsEntry(
                    emailAddress = userEmailAddress,
                    onLogInClick = onLogInClick,
                )

                Divider()

            }

            PacingSettingsEntry(
                isPacingEnabled = isPacingEnabled,
                onSwitchPacing = onSwitchPacing,
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
    SHFTheme(darkTheme = true) {
        SettingsScreen(
            onBackButtonClick = {},
            onLogInClick = {},
            onControllerSetupEntryClick = {},
            userEmailAddress = null,
            toggleLogInEnabled = true,
            isPacingEnabled = true,
            onSwitchPacing = {},
        )
    }
}
