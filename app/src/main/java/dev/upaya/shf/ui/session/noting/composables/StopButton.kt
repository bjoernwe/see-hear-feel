package dev.upaya.shf.ui.session.noting.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.ui.start.StartButton
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .background(color = Color.LightGray)
            .size(36.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_stop_circle_24),
            contentDescription = "End Session",
            tint = MaterialTheme.colors.background,
            modifier = Modifier
                .scale(1.6f)
        )
    }
}


@Preview
@Composable
fun StopButtonPreview() {
    SHFTheme(darkTheme = true) {
        StartButton(
            onClick = {},
        )
    }
}
