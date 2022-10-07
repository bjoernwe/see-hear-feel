package dev.upaya.shf.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SessionSceen(inputEvent: InputEvent?) {

    val interactionSource = remember { MutableInteractionSource() }

    // Simulate a key press on value change
    LaunchedEffect(inputEvent) {
        val press = PressInteraction.Press(Offset.Zero)
        interactionSource.emit(press)
        interactionSource.emit(PressInteraction.Release(press))
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .clickable(
                onClick = {},
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = false, radius = 100.dp),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (inputEvent != null) {
            FadingText(text = inputEvent.inputKey.name)
        }
    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) { SessionSceen(inputEvent = InputEvent(InputKey.KEY_1)) }
}
