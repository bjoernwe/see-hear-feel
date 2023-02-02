package dev.upaya.shf.ui.exercises.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.exerciselist.exampleExercises
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList(
    exercises: Map<ExerciseId, ExerciseConfig>,
    modifier: Modifier = Modifier,
    onClick: (ExerciseId) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(exercises.toList()) { exc ->
            ExerciseEntry(exerciseEntry = exc, onClick = onClick)
            Divider(color = Color.Black)
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
