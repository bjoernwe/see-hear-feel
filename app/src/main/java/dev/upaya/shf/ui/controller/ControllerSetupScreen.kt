package dev.upaya.shf.ui.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerSetupScreen(
    inputKey: InputKey?,
) {

    SetStatusBarColor()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Controller(
            key = inputKey,
            //inputKey = inputKey,
            //modifier = Modifier
            //    .fillMaxSize(.5f)
        )
    }
}


@Preview
@Composable
fun ControllerSetupScreenPreview() {
    SHFTheme(darkTheme = true) {
        ControllerSetupScreen(
            inputKey = InputKey.KEY_B,
        )
    }
}
