package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.labels.labelFeel
import dev.upaya.shf.exercises.labels.labelHear
import dev.upaya.shf.exercises.labels.labelSee
import dev.upaya.shf.ui.input.LabelFreqs
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun LabelStatsCard(
    labelFreqs: LabelFreqs,
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

            LabelFreqTable(
                labelFreqs = labelFreqs,
            )

        }

    }

}


val exampleLabelFreqs: LabelFreqs = mutableMapOf(
    labelSee to 15,
    labelHear to 5,
    labelFeel to 1,
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
