package dev.upaya.shf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import dev.upaya.shf.SHFViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun MainContent() {

    val viewModel: SHFViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = viewModel.keyEvent, textAlign = TextAlign.Center)
        }

    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) { MainContent() }
}
