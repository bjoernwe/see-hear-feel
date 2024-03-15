package dev.upaya.shf.ui.settings.controller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.data.gamepad.KeyPressStates
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.settings.controller.composables.ControllerVisualization
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerSetupScreen(
    keyPressStates: KeyPressStates,
    onBackButtonClick: () -> Unit,
) {

    SetStatusBarColor(color = MaterialTheme.colors.secondaryVariant)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Controller Setup", color = Color.Black)
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
    ) { scaffoldPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(scaffoldPadding)
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
        ) {
            ControllerVisualization(
                keyPressStates = keyPressStates,
            )
        }

    }
}


@Preview
@Composable
fun ControllerSetupScreenPreview() {
    SHFTheme(darkTheme = true) {
        ControllerSetupScreen(
            keyPressStates = mapOf(),
            onBackButtonClick = {},
        )
    }
}
