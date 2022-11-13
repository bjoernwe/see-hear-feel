package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.ui.session.SessionViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    sessionViewModel: SessionViewModel = hiltViewModel(),
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Statistics") },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) { scaffoldPadding ->

        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
        ) {

            NotingSummaryCard(
                sessionTimeSeconds = sessionViewModel.getSessionLength() ?: 0,
                numNotings = sessionViewModel.getNumEvents(),
            )

            LabelStatsCard(
                labelFreqs = sessionViewModel.getLabelFreqs(),
            )

        }

    }
    
}


@Preview
@Composable
fun StatsScreenPreview() {
    SHFTheme(darkTheme = true) {
        StatsScreen()
    }
}
