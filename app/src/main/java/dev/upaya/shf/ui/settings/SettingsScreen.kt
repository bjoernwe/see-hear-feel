package dev.upaya.shf.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SettingsScreen(
    hasNotificationPermission: Boolean,
    hasAccessibilityPermission: Boolean,
    backgroundSessionPermitted: Boolean,
) {

    Column {

        Row {

            Switch(
                checked = hasNotificationPermission,
                onCheckedChange = null,
            )

            Switch(
                checked = hasAccessibilityPermission,
                onCheckedChange = null,
            )

            Switch(
                checked = backgroundSessionPermitted,
                onCheckedChange = null,
                enabled = backgroundSessionPermitted,
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
        backgroundSessionPermitted = true,
    )
}
