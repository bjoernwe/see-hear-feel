package dev.upaya.shf.ui.session.noting.stats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.theme.surfaceElevation


@Composable
fun StatsCard(
    title: String,
    content: @Composable () -> Unit,
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.primarySurface,
        elevation = surfaceElevation,
        modifier = Modifier
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondaryVariant,
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            content()

        }

    }

}


@Preview
@Composable
fun StatsCardPreview() {
    SHFTheme(darkTheme = true) {
        StatsCard(
            title = "Title"
        ) {
            Text("Body")
        }
    }
}
