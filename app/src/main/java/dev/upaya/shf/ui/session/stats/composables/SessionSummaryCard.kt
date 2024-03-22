package dev.upaya.shf.ui.session.stats.composables

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme
import java.util.*


@Composable
fun SessionSummaryCard(
    sessionDurationSeconds: Int?,
    numNotings: Int?,
) {

    StatsCard(
        title = "Session Summary",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Duration",
                textValue = if (sessionDurationSeconds != null)
                    DateUtils.formatElapsedTime(sessionDurationSeconds.toLong())
                else
                    "N/A",
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = numNotings.toString(),
            )

            val notingsPerSecond: Float = if (numNotings != null && sessionDurationSeconds != null)
                numNotings.toFloat().div(sessionDurationSeconds.toFloat())
            else
                Float.NaN

            StatsEntryText(
                textLabel = "Speed",
                textValue = "%.2f".format(locale = Locale.ENGLISH, notingsPerSecond),
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
