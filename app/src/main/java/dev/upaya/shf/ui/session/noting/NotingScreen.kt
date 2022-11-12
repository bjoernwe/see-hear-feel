package dev.upaya.shf.ui.session.noting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    statsButtonOnClick: () -> Unit = {},
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
            .background(MaterialTheme.colors.background)
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

            IconButton(
                onClick = statsButtonOnClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_bar_chart_24),
                    contentDescription = "Statistics",
                    tint = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                )
            }

            LabelText(
                label = label,
                primaryColor = MaterialTheme.colors.secondary,
                secondaryColor = MaterialTheme.colors.secondaryVariant,
                key = inputEvent,
                modifier = Modifier
                    .align(Alignment.Center)
            )

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
