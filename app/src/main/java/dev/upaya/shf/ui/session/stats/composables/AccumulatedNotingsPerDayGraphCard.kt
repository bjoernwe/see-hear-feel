package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme
import java.time.LocalDate


@Composable
fun AccumulatedNotingsPerDayGraphCard(
    accumulatedNotingsPerDay: List<Pair<LocalDate, Int>>,
) {
    StatsCard(
        title = "Notings Over Time",
    ) {
        Column {
            AccumulatedValuesPerDayGraph(
                data = accumulatedNotingsPerDay,
            )
        }
    }
}


@Preview
@Composable
fun AccumulatedNotingsPerDayGraphCardPreview() {
    SHFTheme(darkTheme = true) {
        AccumulatedNotingsPerDayGraphCard(
            accumulatedNotingsPerDay = listOf(
                Pair(LocalDate.of(2000, 1, 1), 1),
                Pair(LocalDate.of(2000, 1, 3), 2),
                Pair(LocalDate.of(2000, 1, 4), 3),
            ),
        )
    }
}
