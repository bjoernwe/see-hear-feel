package dev.upaya.shf.ui.stats

import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingSummaryCard(
    sessionTimeSeconds: Int,
    numNotings: Int,
) {

    StatsCard(
        title = "Noting",
    ) {

        Column {

            StatsEntryText(
                textLabel = "Time",
                textValue = DateUtils.formatElapsedTime(sessionTimeSeconds.toLong()),
            )

            StatsEntryText(
                textLabel = "Notings",
                textValue = numNotings.toString(),
            )

            StatsEntryText(
                textLabel = "Notings / Sec",
                textValue = numNotings.toFloat().div(sessionTimeSeconds.toFloat()).toString(),
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
