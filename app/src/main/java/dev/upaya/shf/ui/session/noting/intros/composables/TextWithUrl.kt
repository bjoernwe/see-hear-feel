package dev.upaya.shf.ui.session.noting.intros.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ui.theme.SHFTheme


private const val tagUrl = "URL"


@Composable
fun TextWithUrl(
    builder: AnnotatedString.Builder.() -> Unit,
) {

    val uriHandler = LocalUriHandler.current
    val annotatedText = buildAnnotatedString(builder = builder)

    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.body1,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = tagUrl,
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                uriHandler.openUri(annotation.item)
            }
        }
    )

}


private fun AnnotatedString.Builder.appendUrl(
    text: String,
    url: String,
) {

    pushStringAnnotation(
        tag = tagUrl,
        annotation = url,
    )

    withStyle(
        style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)
    ) {
        append(text)
    }

    pop()

}


@Preview
@Composable
fun TextWithUrlPreview() {
    SHFTheme(darkTheme = true) {
        TextWithUrl() {
            append("Click ")
            appendUrl(
                text = "here",
                url = "http://www.google.com",
            )
        }
    }
}
