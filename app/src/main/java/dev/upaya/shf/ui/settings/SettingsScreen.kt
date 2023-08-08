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
import dev.upaya.shf.ui.settings.composables.LockScreenSettingsEntry
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SettingsScreen(
    onBackButtonClick: () -> Unit,
    isLockScreenPreferred: Boolean,
    hasAccessibilityPermission: Boolean,
    onSwitchLockScreenSession: (Boolean) -> Unit,
    onRequestAccessibilitySettings: () -> Unit,
    onControllerSetupEntryClick: () -> Unit,
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

            ControllerSettingsEntry(
                onControllerSetupEntryClick = onControllerSetupEntryClick,
            )

            Divider()

            LockScreenSettingsEntry(
                onRequestAccessibilitySettings,
                hasAccessibilityPermission,
                isLockScreenPreferred,
                onSwitchLockScreenSession
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
            isLockScreenPreferred = true,
            hasAccessibilityPermission = true,
            onSwitchLockScreenSession = {},
            onRequestAccessibilitySettings = {},
            onControllerSetupEntryClick = {},
        )
    }
}
