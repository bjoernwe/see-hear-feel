package dev.upaya.shf.ui.session.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.data.session_stats.AllTimeStats
import dev.upaya.shf.data.session_stats.SessionStats
import dev.upaya.shf.ui.session.stats.composables.AllTimeSummaryCard
import dev.upaya.shf.ui.session.stats.composables.LabelStatsCard
import dev.upaya.shf.ui.session.stats.composables.SessionSummaryCard
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    numEvents: Int,
    sessionDurationSeconds: Int,
    sessionStats: SessionStats?,
    allTimeStats: AllTimeStats?,
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
                    labelFreqs = sessionStats?.labelFreqs,
                )
            }

            item {
                SessionSummaryCard(
                    sessionDurationSeconds = sessionDurationSeconds,
                    numNotings = numEvents,
                )
            }

            item {
                AllTimeSummaryCard(
                    allTimeStats = allTimeStats,
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
            sessionDurationSeconds = 123,
            numEvents = 42,
            sessionStats = SessionStats(
                labelFreqs = mapOf(),
            ),
            allTimeStats = null,
            onBackButtonClick = {},
        )
    }
}
