package dev.upaya.shf.ui.session.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.data.labels.SHFLabel
import dev.upaya.shf.data.session_data.dataclasses.AllTimeStats
import dev.upaya.shf.data.session_data.dataclasses.SessionStats
import dev.upaya.shf.ui.session.stats.composables.AccumulatedNotingsPerDayGraphCard
import dev.upaya.shf.ui.session.stats.composables.AllTimeSummaryCard
import dev.upaya.shf.ui.session.stats.composables.LabelStatsCard
import dev.upaya.shf.ui.session.stats.composables.SessionSummaryCard
import dev.upaya.shf.ui.theme.SHFTheme
import java.time.LocalDate


@Composable
fun StatsScreen(
    labelFrequencies: Map<SHFLabel, Int>?,
    sessionStats: SessionStats?,
    allTimeStats: AllTimeStats?,
    accumulatedNotingsPerDay: List<Pair<LocalDate, Int>>,
    onBackButtonClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Summary", color = MaterialTheme.colors.onSecondary) },
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "back"
                        )
                    }
                },
            )
        }
    ) { scaffoldPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(scaffoldPadding)
        ) {

            item {
                LabelStatsCard(
                    labelFreqs = labelFrequencies,
                )
            }

            item {
                SessionSummaryCard(
                    sessionStats = sessionStats,
                )
            }

            item {
                AllTimeSummaryCard(
                    allTimeStats = allTimeStats,
                )
            }

            item {
                AccumulatedNotingsPerDayGraphCard(
                    accumulatedNotingsPerDay = accumulatedNotingsPerDay,
                )
            }
        }

    }
    
}


@Preview
@Composable
fun StatsScreenPreview() {
    SHFTheme(darkTheme = true) {
        StatsScreen(
            labelFrequencies = mapOf(),
            sessionStats = SessionStats(
                numberOfNotings = 42,
                sessionDurationSeconds = 123,
                amountMindWandering = .1f,
                showMindWandering = true,
            ),
            allTimeStats = null,
            accumulatedNotingsPerDay = listOf(
                Pair(LocalDate.of(2000, 1, 1), 1),
                Pair(LocalDate.of(2000, 1, 3), 2),
                Pair(LocalDate.of(2000, 1, 4), 3),
            ),
            onBackButtonClick = {},
        )
    }
}
