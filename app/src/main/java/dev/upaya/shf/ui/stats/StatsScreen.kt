package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.input.LabelFreqs
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    labelFreqs: LabelFreqs,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Statistics") },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) { scaffoldPadding ->
        LabelStatsCard(
            labelFreqs = labelFreqs,
            modifier = Modifier
                .padding(scaffoldPadding)
        )
    }
    
}


@Preview
@Composable
fun StatsScreenPreview() {
    SHFTheme(darkTheme = true) {
        StatsScreen(
            labelFreqs = exampleLabelFreqs,
        )
    }
}
