package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.exercises.labels.labelFeel
import dev.upaya.shf.exercises.labels.labelHear
import dev.upaya.shf.exercises.labels.labelSee
import dev.upaya.shf.ui.theme.SHFTheme
import java.lang.Float.max


typealias LabelFreqs = Map<Label, Int>


@Composable
fun LabelFreqTable(labelFreqs: LabelFreqs) {

    LazyColumn() {

        items(labelFreqs.entries.toList()) { (label, count) ->

            LabelWithBar(
                label = label.primary.uppercase(),
                barSize = count.toFloat(),
                maxBarSize = labelFreqs.values.maxOrNull()?.toFloat() ?: 0f,
            )

        }

    }

}


@Composable
fun LabelWithBar(
    label: String,
    barSize: Float,
    maxBarSize: Float,
) {

    Row {

        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxSize()
                .align(CenterVertically)
                .weight(.2f)
                .padding(end = 6.dp)
        )

        HorizontalBar(
            size = barSize,
            maxSize = maxBarSize,
            modifier = Modifier
                .weight(1f)
        )

    }

}


@Composable
fun HorizontalBar(
    size: Float,
    maxSize: Float,
    modifier: Modifier = Modifier,
) {

    fun getRelativeSize(size: Float): Float {
        return if (maxSize > 0) {
            size / maxSize
        } else {
            0f
        }
    }

    val barSize: Float = getRelativeSize(size)
    val barSizeComplement = 1f - barSize

    Row(
        modifier = modifier
            .padding(4.dp)
    ) {

        if (barSize > 0.01f) {

            Surface(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .weight(barSize)
                    .clip(RoundedCornerShape(50))
            ) {
                Text(text = "")
            }

            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .weight(max(0.001f, barSizeComplement))
            ) {
                Text(text = "")
            }
        }

    }

}


@Preview
@Composable
fun LabelFreqTablePreview() {

    val labelFreqs: LabelFreqs = mapOf(
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
