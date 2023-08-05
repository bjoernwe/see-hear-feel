package dev.upaya.shf.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.session.noting.composables.SettingsButton
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StartScreen(
    onStartButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
) {

    SetStatusBarColor()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            SettingsButton(
                onClick = onSettingsButtonClick,
                modifier = Modifier
                    .align(Alignment.TopEnd),
            )

            Blur(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(172.dp)
            )

            StartButton(
                onClick = onStartButtonClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
            )

        }

    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) {
        StartScreen(
            onStartButtonClick = {},
            onSettingsButtonClick = {},
        )
    }
}
