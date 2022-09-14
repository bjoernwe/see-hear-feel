package dev.upaya.shf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun MainContent() {

    SHFTheme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Greeting("Android")
        }

    }

}
