package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.exampleExercises
import dev.upaya.shf.ui.ExerciseEntry
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseListScreen(
    exercises: List<ExerciseConfig>,
    onClick: (ExerciseConfig) -> Unit = {},
) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(color = MaterialTheme.colors.secondaryVariant)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Noting Exercises") },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
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
        ExerciseListScreen(
            exercises = exampleExercises,
        )
    }
}
