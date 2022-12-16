package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.exampleExercises
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList(
    exercises: Map<String, ExerciseConfig>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(exercises.toList()) { cfg ->
            ExerciseEntry(exerciseEntry = cfg, onClick = onClick)
            Divider()
        }
    }
}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseList(
            exercises = exampleExercises,
        )
    }
}
