package dev.upaya.shf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import dev.upaya.shf.SHFViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun MainContent() {

    val viewModel: SHFViewModel = viewModel()

    SHFTheme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Greeting(viewModel.keyEvent)
        }

    }

}
