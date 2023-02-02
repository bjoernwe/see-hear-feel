package dev.upaya.shf.ui.session.noting.composables

import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun FadingText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 50.sp,
    color: Color = MaterialTheme.colors.secondary,
    fontFamily: FontFamily? = null,
    key: Any? = null,
    fadingDurationMillis: Int = 800,
) {

    val alpha = remember { Animatable(1f) }

    LaunchedEffect(key, text) {
        alpha.snapTo(1f)
        alpha.animateTo(0f, animationSpec = tween(fadingDurationMillis))
    }

    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamily,
        textAlign = TextAlign.Center,
        modifier = modifier.alpha(alpha.value)
    )

}


@Preview
@Composable
fun FadingTextPreview() {
    FadingText(text = "fading text")
}
