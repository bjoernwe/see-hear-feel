package dev.upaya.shf.ui.session.noting.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.LabelFreqs
import dev.upaya.shf.ui.session.noting.stats.composables.LabelStatsCard
import dev.upaya.shf.ui.session.noting.stats.composables.NotingSummaryCard
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    sessionLength: Int?,
    numEvents: Int,
    labelFreqs: LabelFreqs,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Statistics", color = MaterialTheme.colors.onSecondary) },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) { scaffoldPadding ->

        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
        ) {

            NotingSummaryCard(
                sessionTimeSeconds = sessionLength ?: 0,
                numNotings = numEvents,
            )

            LabelStatsCard(
                labelFreqs = labelFreqs,
            )

        }

    }
    
}


@Preview
@Composable
fun StatsScreenPreview() {
    SHFTheme(darkTheme = true) {
        StatsScreen(
            sessionLength = 123,
            numEvents = 42,
            labelFreqs = mapOf(Label("foo") to 1, Label("bar") to 2),
        )
    }
}
