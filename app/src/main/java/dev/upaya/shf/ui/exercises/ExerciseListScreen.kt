package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.exampleExercises
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
        ExerciseList(
            exercises = exercises,
            modifier = Modifier.padding(padding),
            onClick = onClick,
        )
    }
}


@Preview
@Composable
fun ExerciseListScreenPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseListScreen(
            exercises = exampleExercises,
        )
    }
}
