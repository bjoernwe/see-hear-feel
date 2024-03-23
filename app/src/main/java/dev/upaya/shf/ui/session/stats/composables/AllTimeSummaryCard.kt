package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun AllTimeSummaryCard(
    numEventsInDB: Int,
    numOfDays: Int,
) {

    StatsCard(
        title = "All-Time Summary",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Notings",
                textValue = numEventsInDB.toString(),
            )

            StatsEntryText(
                textLabel = "Days",
                textValue = numOfDays.toString(),
            )

        }

    }

}


@Preview
@Composable
fun AllTimeSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        AllTimeSummaryCard(
            numEventsInDB = 123,
            numOfDays = 7,
        )
    }
}
