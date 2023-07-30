package dev.upaya.shf.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SettingsScreen(
    hasAccessibilityPermission: Boolean,
    isLockScreenSessionEnabled: Boolean,
    onSwitchLockScreenSession: (Boolean) -> Unit,
) {

    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {

            Column {

                Text(
                    text = "Session on Lock-Screen",
                    modifier = Modifier
                    //.fillMaxWidth()
                )

                Text(text = "Accessibility service: $hasAccessibilityPermission")

            }

            Switch(
                checked = isLockScreenSessionEnabled,
                onCheckedChange = onSwitchLockScreenSession,
                modifier = Modifier
                    .padding(4.dp)
            )

        }


    }

}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        hasAccessibilityPermission = true,
        isLockScreenSessionEnabled = true,
        onSwitchLockScreenSession = {},
    )
}
