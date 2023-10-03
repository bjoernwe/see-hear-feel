package dev.upaya.shf.ui.session.stats.composables

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme
import java.util.*


@Composable
fun NotingSummaryCard(
    sessionTimeSeconds: Int?,
    numNotings: Int?,
) {

    StatsCard(
        title = "Session",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Duration",
                textValue = if (sessionTimeSeconds != null)
                    DateUtils.formatElapsedTime(sessionTimeSeconds.toLong())
                else
                    "N/A",
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = numNotings.toString(),
            )

            val notingsPerSecond: Float = if (numNotings != null && sessionTimeSeconds != null)
                numNotings.toFloat().div(sessionTimeSeconds.toFloat())
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
fun NotingSummaryCardPreview() {
    SHFTheme(darkTheme = true) {
        NotingSummaryCard(
            sessionTimeSeconds = 60,
            numNotings = 512,
        )
    }
}
