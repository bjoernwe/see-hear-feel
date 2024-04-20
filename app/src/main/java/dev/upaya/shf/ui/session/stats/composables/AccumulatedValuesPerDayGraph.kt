package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.theme.SHFTheme
import java.time.LocalDate


@Composable
fun AccumulatedValuesPerDayGraph(
    data: List<Pair<LocalDate, Int>>,
    minNumDaysForGraph: Int = 2,
) {
    if (data.size >= minNumDaysForGraph) {
        DataGraph(data = data)
    } else {
        PlaceholderGraph()
    }
}


@Composable
private fun DataGraph(
    data: List<Pair<LocalDate, Int>>,
) {
    val colorLine = MaterialTheme.colors.secondary
    val colorFill = MaterialTheme.colors.secondaryVariant

    Spacer(
        modifier = Modifier
            .aspectRatio(2.5f)
            .fillMaxSize()
            .drawWithCache {

                val path = calcGraphPath(data = data, size = size, close = false)
                val closedPath = calcGraphPath(data = data, size = size, close = true)

                onDrawBehind {
                    drawPath(closedPath, color = colorFill, style = Fill)
                    drawPath(path, color = colorLine, style = Stroke(3.dp.toPx()))
                }
            }
    )
}


@Composable
private fun PlaceholderGraph() {
    Box(
        modifier = Modifier
            .aspectRatio(2.5f)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("[not enough data]", color = Color.DarkGray)
    }
}


private fun calcGraphPath(
    data: List<Pair<LocalDate, Int>>,
    size: Size,
    close: Boolean = false,
): Path {

    val firstDay = data.first().first.toEpochDay()
    val lastDay = data.last().first.toEpochDay()
    val dayRange = lastDay - firstDay
    val lastValue = data.last().second

    val path = Path()

    data.forEachIndexed { i, dateAndValue ->

        val day = dateAndValue.first.toEpochDay()
        val value = dateAndValue.second

        val x: Float = size.width * (day - firstDay) / dayRange
        val y: Float = size.height - value * size.height / lastValue

        if (i == 0)
            path.moveTo(x, y)
        else
            path.lineTo(x, y)
    }

    if (close) {
        path.lineTo(size.width, size.height)
        path.lineTo(0f, size.height)
        path.close()
    }

    return path
}


@Preview
@Composable
fun AccumulatedValuesPerDayGraphPreview() {
    SHFTheme(darkTheme = true) {
        AccumulatedValuesPerDayGraph(
            data = listOf(
                Pair(LocalDate.of(2000, 1, 1), 1),
                Pair(LocalDate.of(2000, 1, 3), 2),
                Pair(LocalDate.of(2000, 1, 4), 3),
            ),
        )
    }
}
