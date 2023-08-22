package dev.upaya.shf.ui.session.stats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun HorizontalBar(
    size: Float,
    modifier: Modifier = Modifier,
) {

    val barSize: Float = size.coerceIn(0f, 1f)
    val barSizeComplement = 1f - barSize

    Row(
        modifier = modifier
            .padding(4.dp)
    ) {

        if (barSize > 0f) {

            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .weight(barSize)
                    .scale(scaleX = 1f, scaleY = .6f)
            ) {
                Text(text = "")
            }

            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .weight(java.lang.Float.max(0.001f, barSizeComplement))
            ) {
                Text(text = "")
            }
        }

    }

}


@Preview
@Composable
fun HorizontalBarPreview() {
    SHFTheme(darkTheme = true) {
        HorizontalBar(
            size = .66f,
        )
    }
}
