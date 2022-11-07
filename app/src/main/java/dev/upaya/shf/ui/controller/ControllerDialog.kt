package dev.upaya.shf.ui.controller

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerDialog(
    visible: MutableState<Boolean>,
    inputKey: InputKey?,
    onKeyEvent: (KeyEvent) -> Boolean,
) {
    AlertDialog(
        onDismissRequest = {
            visible.value = false
        },
        title = {
            Text("Controller Setup")
        },
        confirmButton = {
            TextButton(onClick = {
                visible.value = false
            }) {
                Text("OK")
            }
        },
        text = {
            ControllerVisualization(
                inputKey = inputKey,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier.onKeyEvent { keyEvent -> onKeyEvent(keyEvent) }
    )
}


@Preview
@Composable
fun ControllerDialogPreview() {

    val visible = remember { mutableStateOf(true) }
    val inputKey = InputKey.KEY_B

    SHFTheme(darkTheme = true) {
        ControllerDialog(
            visible = visible,
            inputKey = inputKey,
            onKeyEvent = { true },
        )
    }
}
