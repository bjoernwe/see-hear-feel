package dev.upaya.shf.ui.session.noting.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SettingsButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = "Settings",
            tint = Color.White,
        )
    }
}


@Preview
@Composable
fun SettingsButtonPreview() {
    SHFTheme(darkTheme = true) {
        SettingsButton(
            onClick = {},
        )
    }
}
