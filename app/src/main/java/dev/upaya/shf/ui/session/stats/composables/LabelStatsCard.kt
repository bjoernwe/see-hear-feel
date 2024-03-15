package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.data.gamepad_input.SHFLabel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun LabelStatsCard(
    labelFreqs: Map<SHFLabel, Int>?,
    modifier: Modifier = Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {

        StatsCard(
            title = "Labels",
        ) {

            if (labelFreqs != null) {
                LabelFreqTable(
                    labelFreqs = labelFreqs,
                )
            }

        }

    }

}


val exampleLabelFreqs: Map<SHFLabel, Int> = mutableMapOf(
    SHFLabel.SEE to 15,
    SHFLabel.HEAR to 5,
    SHFLabel.FEEL to 1,
)


@Preview
@Composable
fun LabelStatsCardPreview() {

    SHFTheme(darkTheme = true) {
        LabelStatsCard(
            labelFreqs = exampleLabelFreqs,
        )
    }

}
