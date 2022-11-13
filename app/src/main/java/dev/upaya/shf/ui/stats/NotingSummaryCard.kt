package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingSummaryCard() {

    StatsCard(
        title = "Noting",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Session Time",
                textValue = "0:42:00",
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = "512",
            )

        }

    }

}


@Preview
@Composable
fun NotingSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        NotingSummaryCard()
    }
}
