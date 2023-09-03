package dev.upaya.shf.ui.session.stats.composables

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme
import java.util.*


@Composable
fun NotingSummaryCard(
    sessionTimeSeconds: Int,
    numNotings: Int,
) {

    StatsCard(
        title = "Session",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Duration",
                textValue = DateUtils.formatElapsedTime(sessionTimeSeconds.toLong()),
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = numNotings.toString(),
            )

            val notingsPerSecond = numNotings.toFloat().div(sessionTimeSeconds.toFloat())

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
