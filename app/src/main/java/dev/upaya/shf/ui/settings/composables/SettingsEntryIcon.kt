package dev.upaya.shf.ui.settings.composables

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R


@Composable
fun SettingsEntryIcon(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int = R.drawable.baseline_settings_24,
) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = "Settings Icon",
        tint = Color.White,
        modifier = modifier
    )
}


@Preview
@Composable
fun SettingsEntryIconPreview() {
    SettingsEntryIcon(
        id = R.drawable.baseline_settings_24,
    )
}
