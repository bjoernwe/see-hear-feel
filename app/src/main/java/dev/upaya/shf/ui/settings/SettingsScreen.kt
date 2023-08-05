package dev.upaya.shf.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SettingsScreen(
    isLockScreenPreferred: Boolean,
    hasAccessibilityPermission: Boolean,
    onSwitchLockScreenSession: (Boolean) -> Unit,
    onRequestAccessibilitySettings: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings", color = Color.White) },
                //backgroundColor = MaterialTheme.colors.secondary,
                //navigationIcon = { Icon(painter = painterResource(R.drawable.baseline_arrow_back_ios_24), contentDescription = "") },
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(4.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                //.fillMaxWidth()
                //.wrapContentHeight()
                .padding(8.dp)
            ) {

                Icon(
                    painter = painterResource(R.drawable.baseline_screen_lock_portrait_24),
                    contentDescription = "Locked Screen",
                    modifier = Modifier
                        .size(36.dp)
                        //.padding(8.dp)
                        //.align(Alignment.Top)
                        //.wrapContentSize()
                        //.padding(8.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 10.dp)
                        .clickable { onRequestAccessibilitySettings() }
                ) {

                    Text(
                        text = "Session on lock screen",
                        modifier = Modifier
                    )

                    /*Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.baseline_check_box_24),
                            contentDescription = "check",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(14.dp)
                        )

                        Text(
                            text = "Requires accessibility service: ",
                            color = Color.Gray,
                            modifier = Modifier
                                //.padding(start = 4.dp)
                        )

                        Text(
                            text = "$hasAccessibilityPermission",
                            color = Color.Gray,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .clickable(onClick = onRequestAccessibilitySettings)
                        )

                    }*/

                }

                Switch(
                    checked = hasAccessibilityPermission && isLockScreenPreferred,
                    onCheckedChange = onSwitchLockScreenSession,
                    enabled = hasAccessibilityPermission,
                    modifier = Modifier
                        //.padding(4.dp)
                )

            }


        }

    }

}


@Preview
@Composable
fun SettingsScreenPreview() {
    SHFTheme(darkTheme = true) {
        SettingsScreen(
            isLockScreenPreferred = true,
            hasAccessibilityPermission = true,
            onSwitchLockScreenSession = {},
            onRequestAccessibilitySettings = {},
        )
    }
}
