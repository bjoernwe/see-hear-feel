package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.data.gamepad_input.SHFLabel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun LabelFreqTable(labelFreqs: Map<SHFLabel, Int>) {

    val maxLabelCount = labelFreqs.values.maxOrNull()?.toFloat() ?: 1f

    LazyColumn {

        items(labelFreqs.entries.toList()) { (label, count) ->

            StatsEntryBar(
                text = label.name.uppercase(),
                barSize = count.toFloat() / maxLabelCount,
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
