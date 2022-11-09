package dev.upaya.shf.ui.controller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.input.InputViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerSetupScreen(
    inputViewModel: InputViewModel = hiltViewModel(),
) {

    SetStatusBarColor()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
    ) {

        val keyPressStates by inputViewModel.keyPressStates.collectAsState()

        Controller(
            keyPressStates = keyPressStates,
        )
    }
}


@Preview
@Composable
fun ControllerSetupScreenPreview() {
    SHFTheme(darkTheme = true) {
        ControllerSetupScreen()
    }
}
