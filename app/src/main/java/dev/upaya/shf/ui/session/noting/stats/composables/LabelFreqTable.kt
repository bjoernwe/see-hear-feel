package dev.upaya.shf.ui.session.noting.stats.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.labels.labelFeel
import dev.upaya.shf.exercises.labels.labelHear
import dev.upaya.shf.exercises.labels.labelSee
import dev.upaya.shf.inputs.input_events.LabelFreqs
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun LabelFreqTable(labelFreqs: LabelFreqs) {

    val maxLabelCount = labelFreqs.values.maxOrNull()?.toFloat() ?: 1f

    LazyColumn {

        items(labelFreqs.entries.toList()) { (label, count) ->

            StatsEntryBar(
                text = label.primary.uppercase(),
                barSize = count.toFloat() / maxLabelCount,
                columnWeight = .5f,
            )

        }

    }

}


@Preview
@Composable
fun LabelFreqTablePreview() {

    val labelFreqs: LabelFreqs = mutableMapOf(
        labelSee to 15,
        labelHear to 5,
        labelFeel to 1,
    )

    SHFTheme(darkTheme = true) {
        LabelFreqTable(
            labelFreqs = labelFreqs,
        )
    }

}
