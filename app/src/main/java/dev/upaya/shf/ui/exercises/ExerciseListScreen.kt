package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.upaya.shf.exercises.ExerciseConfig
import dev.upaya.shf.exercises.ExerciseViewModel
import dev.upaya.shf.ui.ExerciseEntry
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseListScreen(
    viewModel: ExerciseViewModel = viewModel(),
    onClick: (ExerciseConfig) -> Unit = {},
) {

    val exercises by viewModel.exercises.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exercise List") },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(exercises) { cfg ->
                    ExerciseEntry(cfg = cfg, onClick = onClick)
                }
            }
        }
    }
}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseListScreen()
    }
}
