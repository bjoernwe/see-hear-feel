package dev.upaya.shf.ui.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun Blur(
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
    ) {

        Icon(
            painter = painterResource(id = R.drawable.baseline_blur_on_24),
            contentDescription = "Blur",
            tint = Color.Gray,
            modifier = Modifier
                .matchParentSize()
                .offset((-.5).dp, (-.5).dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.baseline_blur_on_24),
            contentDescription = "Blur",
            tint = Color.Black,
            modifier = Modifier
                .matchParentSize()
                .offset(.5.dp, .5.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.baseline_blur_on_24),
            contentDescription = "Blur",
            tint = MaterialTheme.colors.background,
            modifier = Modifier
                .matchParentSize()
        )

    }

}


@Preview
@Composable
fun BlurPreview() {
    SHFTheme(darkTheme = true) {
        Blur(
            modifier = Modifier
                .size(128.dp)
        )
    }
}
