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
import dev.upaya.shf.keymaps.InputKey
import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SessionContent(lastInputKey: InputKey?, labelMap: LabelMap = LabelMapSHF) {

    val interactionSource = remember { MutableInteractionSource() }

    // Simulate a key press on value change
    LaunchedEffect(lastInputKey) {
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
        lastInputKey?.let { key -> FadingText(text = labelMap.getLabel(key)) }
    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) { SessionContent(lastInputKey = null) }
}
