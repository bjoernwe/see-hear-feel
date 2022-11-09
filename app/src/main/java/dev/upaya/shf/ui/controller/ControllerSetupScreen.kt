package dev.upaya.shf.ui.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerSetupScreen(
    inputEvent: InputEvent?,
) {

    SetStatusBarColor()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Controller(
            inputEvent = inputEvent,
        )
    }
}


@Preview
@Composable
fun ControllerSetupScreenPreview() {
    SHFTheme(darkTheme = true) {
        ControllerSetupScreen(
            inputEvent = null,
        )
    }
}
