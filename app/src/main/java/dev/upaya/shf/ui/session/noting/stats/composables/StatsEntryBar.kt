package dev.upaya.shf.ui.session.noting.stats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsEntryBar(
    text: String,
    barSize: Float,
    columnWeight: Float = .5f,
) {

    Row(
        modifier = Modifier
            .wrapContentHeight()
    ) {

        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .weight(columnWeight)
                .padding(end = 6.dp)
        )

        Spacer(
            modifier = Modifier
                .width(6.dp)
        )

        HorizontalBar(
            size = barSize,
            modifier = Modifier
                .weight(1f)
        )

    }

}


@Preview
@Composable
fun StatsEntryBarPreview() {
    SHFTheme(darkTheme = true) {
        StatsEntryBar(
            text = "Label",
            barSize = .66f,
        )
    }
}
