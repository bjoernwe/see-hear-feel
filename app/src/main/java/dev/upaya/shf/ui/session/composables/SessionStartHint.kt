package dev.upaya.shf.ui.session.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.theme.varelaFontFamily


@Composable
fun SessionStartHint(
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {

        Icon(
            painter = painterResource(R.drawable.ic_round_videogame_asset_24),
            contentDescription = "Game Pad",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(42.dp)
        )

        Text(
            text = "click button to start",
            fontFamily = varelaFontFamily,
            color = Color.Gray,
        )

    }

}


@Preview
@Composable
private fun SessionStartHintPreview() {
    SHFTheme {
        SessionStartHint()
    }
}
