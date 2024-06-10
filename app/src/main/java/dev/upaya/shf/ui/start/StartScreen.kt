package dev.upaya.shf.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.session.composables.SettingsButton
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.theme.varelaFontFamily


@Composable
fun StartScreen(
    onStartButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    isLoginEnabled: Boolean,
    onSignInButtonClick: () -> Unit,
    signedInUserEmail: String? = null,
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Blur(modifier = Modifier.size(128.dp))
                Spacer(modifier = Modifier.height(48.dp))
                Text("see / hear / feel".uppercase(), fontWeight= FontWeight.Bold, color=MaterialTheme.colors.secondary, fontFamily = varelaFontFamily)
                Spacer(modifier = Modifier.height(14.dp))
                Text("click play to start session", color= Color.Gray, fontFamily = varelaFontFamily)
            }

            if (isLoginEnabled) {
                TextButton(onClick = onSignInButtonClick) {
                    Text(signedInUserEmail ?: "Sign in")
                }
            }

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
            onSignInButtonClick = {},
            isLoginEnabled = true,
        )
    }
}
