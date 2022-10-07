package dev.upaya.shf.ui.session

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
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SessionScreen(
    viewModel: SessionViewModel = viewModel(),
) {

    val inputEvent by viewModel.inputEvent.collectAsState()
    val label: String by viewModel.label.collectAsState(initial = "")

    // Simulate a key press on value change
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(inputEvent) {
        interactionSource.simulatePress()
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
        FadingText(text = label, key = inputEvent)
    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) { SessionScreen() }
}


suspend fun MutableInteractionSource.simulatePress() {
    val press = PressInteraction.Press(Offset.Zero)
    this.emit(press)
    this.emit(PressInteraction.Release(press))
}
