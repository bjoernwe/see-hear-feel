package dev.upaya.shf.ui.session.noting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.R
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.KeepScreenOn
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.input.InputViewModel
import dev.upaya.shf.ui.session.LabelText
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.simulatePress
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingScreen(
    onSessionEnd: () -> Unit,
    inputViewModel: InputViewModel = hiltViewModel(),
    sessionViewModel: SessionViewModel = hiltViewModel(),
    onStopButtonClick: () -> Unit = {},
) {

    SetStatusBarColor()
    KeepScreenOn()

    DisposableEffect(sessionViewModel) {
        onDispose(onSessionEnd)
    }

    val inputEvent by inputViewModel.inputEvent.collectAsState()
    val label: Label by sessionViewModel.label.collectAsState(initial = Label(""))

    // Simulate a key press on value change
    val interactionSource = remember { MutableInteractionSource() }.apply {
        LaunchedEffect(inputEvent) { simulatePress() }
    }

    Column(
        Modifier
            .fillMaxSize()
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
        ) {

            LabelText(
                label = label,
                primaryColor = MaterialTheme.colors.secondary,
                secondaryColor = MaterialTheme.colors.secondaryVariant,
                key = inputEvent,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            IconButton(
                onClick = onStopButtonClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_stop_circle_24),
                    contentDescription = "End Session",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .scale(2f)
                )
            }

        }

    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) {
        NotingScreen(
            onSessionEnd = {}
        )
    }
}
