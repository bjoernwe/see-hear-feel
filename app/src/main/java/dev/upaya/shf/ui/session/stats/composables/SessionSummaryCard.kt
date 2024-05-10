package dev.upaya.shf.ui.session.stats.composables

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun SessionSummaryCard(
    sessionDurationSeconds: Long?,
    numNotings: Int?,
) {

    StatsCard(
        title = "Session Summary",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Duration",
                textValue = if (sessionDurationSeconds != null)
                    DateUtils.formatElapsedTime(sessionDurationSeconds)
                else
                    "N/A",
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = numNotings.toString(),
            )

        }

    }

}


@Preview
@Composable
fun SessionSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        SessionSummaryCard(
            sessionDurationSeconds = 60,
            numNotings = 512,
        )
    }
}
