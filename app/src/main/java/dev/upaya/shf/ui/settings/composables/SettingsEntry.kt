package dev.upaya.shf.ui.settings.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
@OptIn(ExperimentalMaterialApi::class)
internal fun SettingsEntry(
    settingsEntryIcon: @Composable () -> Unit = { SettingsEntryIcon() },
    primaryText: String,
    secondaryText: String? = null,
    onClick: () -> Unit = {},
    settingsEntryOption: @Composable (() -> Unit)? = null,
) {

    Surface(onClick = onClick) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier.size(32.dp)
            ) {
                settingsEntryIcon()
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 10.dp)
            ) {

                Text(
                    text = primaryText,
                )

                if (secondaryText != null) {

                    Text(
                        text = secondaryText,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        //modifier = Modifier
                        //.padding(start = 4.dp)
                    )

                }

            }

            if (settingsEntryOption != null) {
                Spacer(modifier = Modifier.width(6.dp))
                settingsEntryOption()
            }

        }
    }

}


@Preview
@Composable
fun SettingsEntryPreview() {
    SHFTheme(darkTheme = true) {
        SettingsEntry(
            primaryText = "Primary Text",
            secondaryText = "Secondary Text Secondary Text Secondary Text Secondary Text Secondary Text"
        )
    }
}
