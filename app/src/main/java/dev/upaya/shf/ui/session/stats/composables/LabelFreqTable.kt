package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun LabelFreqTable(labelFreqs: Map<SHFLabel, Int>) {

    val maxLabelCount = labelFreqs.values.maxOrNull()?.toFloat() ?: 1f

    Column {

        if (labelFreqs.getOrDefault(SHFLabel.SEE, 0) > 0) {
            StatsEntryBar(
                text = SHFLabel.SEE.name.uppercase(),
                barSize = labelFreqs[SHFLabel.SEE]?.toFloat()?.div(maxLabelCount) ?: 0f,
                columnWeight = .5f,
            )
        }

        if (labelFreqs.getOrDefault(SHFLabel.HEAR, 0) > 0) {
            StatsEntryBar(
                text = SHFLabel.HEAR.name.uppercase(),
                barSize = labelFreqs[SHFLabel.HEAR]?.toFloat()?.div(maxLabelCount) ?: 0f,
                columnWeight = .5f,
            )
        }

        if (labelFreqs.getOrDefault(SHFLabel.FEEL, 0) > 0) {
            StatsEntryBar(
                text = SHFLabel.FEEL.name.uppercase(),
                barSize = labelFreqs[SHFLabel.FEEL]?.toFloat()?.div(maxLabelCount) ?: 0f,
                columnWeight = .5f,
            )
        }

        if (labelFreqs.getOrDefault(SHFLabel.GONE, 0) > 0) {
            StatsEntryBar(
                text = SHFLabel.GONE.name.uppercase(),
                barSize = labelFreqs[SHFLabel.GONE]?.toFloat()?.div(maxLabelCount) ?: 0f,
                columnWeight = .5f,
            )
        }
    }

}


@Preview
@Composable
fun LabelFreqTablePreview() {

    val labelFreqs: Map<SHFLabel, Int> = mutableMapOf(
        SHFLabel.SEE to 15,
        SHFLabel.HEAR to 5,
        SHFLabel.FEEL to 1,
    )

    SHFTheme(darkTheme = true) {
        LabelFreqTable(
            labelFreqs = labelFreqs,
        )
    }

}
