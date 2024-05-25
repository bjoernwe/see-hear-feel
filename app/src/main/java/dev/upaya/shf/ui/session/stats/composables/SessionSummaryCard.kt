package dev.upaya.shf.ui.session.stats.composables

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.data.session_data.dataclasses.SessionStats
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SessionSummaryCard(sessionStats: SessionStats?) {

    StatsCard(
        title = "Session Summary",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Notings",
                textValue = sessionStats?.numberOfNotings.toString(),
            )

            StatsEntryText(
                textLabel = "Duration",
                textValue = if (sessionStats?.sessionDurationSeconds != null)
                    DateUtils.formatElapsedTime(sessionStats.sessionDurationSeconds)
                else
                    "N/A",
            )

            if (sessionStats?.showMindWandering == true) {
                StatsEntryText(
                    textLabel = "Mind Wandering",
                    textValue = "${sessionStats.amountMindWandering.times(100).toInt()}%",
                )
            }

        }

    }

}


@Preview
@Composable
fun SessionSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        SessionSummaryCard(
            sessionStats = SessionStats(
                numberOfNotings = 512,
                sessionDurationSeconds = 60,
                amountMindWandering = .1f,
                showMindWandering = true,
            )
        )
    }
}
