package dev.upaya.shf.ui.session.noting

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_stop_circle_24),
            contentDescription = "End Session",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .scale(2f)
        )
    }
}


@Preview
@Composable
fun StopButtonPreview() {
    SHFTheme(darkTheme = true) {
        StopButton(
            onClick = {},
        )
    }
}
