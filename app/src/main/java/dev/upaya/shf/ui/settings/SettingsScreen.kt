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
    hasNotificationPermission: Boolean,
    hasAccessibilityPermission: Boolean,
) {

    Column {

        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = "Session on Lock-Screen",
                modifier = Modifier
                    //.fillMaxWidth()
            )

            Switch(
                checked = hasAccessibilityPermission,
                onCheckedChange = null,
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
        hasNotificationPermission = true,
        hasAccessibilityPermission = true,
    )
}
