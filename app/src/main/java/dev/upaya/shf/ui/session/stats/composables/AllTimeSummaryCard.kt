package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.data.session_data.AllTimeStats
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun AllTimeSummaryCard(
    allTimeStats: AllTimeStats?,
) {

    StatsCard(
        title = "All-Time Summary",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Notings",
                textValue = allTimeStats?.numNotings?.toString() ?: "N/A",
            )

            StatsEntryText(
                textLabel = "Sessions",
                textValue = allTimeStats?.numSessions?.toString() ?: "N/A",
            )

            StatsEntryText(
                textLabel = "Days",
                textValue = allTimeStats?.numDays?.toString() ?: "N/A",
            )

        }

    }

}


@Preview
@Composable
fun AllTimeSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        AllTimeSummaryCard(
            allTimeStats = null,
        )
    }
}
