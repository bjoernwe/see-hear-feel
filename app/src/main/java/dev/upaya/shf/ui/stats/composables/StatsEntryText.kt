package dev.upaya.shf.ui.stats.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsEntryText(
    textLabel: String,
    textValue: String,
    columnWeight: Float = .5f,
) {

    Row {

        Text(
            text = textLabel.uppercase(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .weight(columnWeight)
                .padding(end = 6.dp)
        )

        Text(
            text = textValue.uppercase(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 6.dp)
        )

    }

}


@Preview
@Composable
fun StatsEntryTextPreview() {
    SHFTheme(darkTheme = true) {
        StatsEntryText(
            textLabel = "Name",
            textValue = "Value",
        )
    }
}
