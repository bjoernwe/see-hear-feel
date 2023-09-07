package dev.upaya.shf.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.Label
import dev.upaya.shf.data.sources.InputEvent
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.session.composables.LabelText
import dev.upaya.shf.ui.session.composables.SessionStartHint
import dev.upaya.shf.ui.session.composables.StopButton
import dev.upaya.shf.ui.simulatePress
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingScreen(
    label: Label,
    inputEvent: InputEvent?,
    onStopButtonClick: () -> Unit,
    numInputEvents: Int,
) {

    SetStatusBarColor()
    KeepScreenOn()

    // Simulate a key press on value change
    val interactionSource = remember { MutableInteractionSource() }.apply {
        LaunchedEffect(inputEvent) { simulatePress() }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .clickable(
                onClick = {},
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = false, radius = 100.dp),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            if (numInputEvents > 0) {

                LabelText(
                    label = label,
                    primaryColor = MaterialTheme.colors.secondary,
                    secondaryColor = MaterialTheme.colors.secondaryVariant,
                    key = inputEvent,
                    modifier = Modifier
                        .align(Alignment.Center)
                )

            } else {

                SessionStartHint(
                    modifier = Modifier
                        .align(Alignment.Center)
                )

            }

            StopButton(
                onClick = onStopButtonClick,
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
        NotingScreen(
            label = Label("label"),
            inputEvent = null,
            onStopButtonClick = {},
            numInputEvents = 0,
        )
    }
}
