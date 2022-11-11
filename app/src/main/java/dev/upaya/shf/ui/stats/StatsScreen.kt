package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.labels.labelFeel
import dev.upaya.shf.exercises.labels.labelHear
import dev.upaya.shf.exercises.labels.labelSee
import dev.upaya.shf.ui.input.LabelFreqs
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    labelFreqs: LabelFreqs,
) {

    Column {
        LabelFreqTable(
            labelFreqs = labelFreqs,
        )
    }

}


@Preview
@Composable
fun StatsScreenPreview() {

    val labelFreqs: LabelFreqs = mutableMapOf(
        labelSee to 15,
        labelHear to 5,
        labelFeel to 1,
    )

    SHFTheme(darkTheme = true) {
        StatsScreen(
            labelFreqs = labelFreqs,
        )
    }
}
