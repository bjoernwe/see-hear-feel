package dev.upaya.shf.ui.session.noting.intros.composables

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun YouTubeImage(videoId: String) {

    val uriHandler = LocalUriHandler.current

    AsyncImage(
        modifier = Modifier.clickable {
            uriHandler.openUri("https://youtube.com/watch?v=${videoId}")
        },
        model = "https://img.youtube.com/vi/${videoId}/maxresdefault.jpg",
        contentDescription = null,
    )

}


@Preview
@Composable
fun YouTubeImagePreview() {
    SHFTheme(darkTheme = true) {
        YouTubeImage(
            videoId = "StBTuX0tqU8",
        )
    }
}
