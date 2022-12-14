package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.R
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.exerciselist.exampleExercises
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseListScreen(
    exercises: Map<ExerciseId, ExerciseConfig>,
    onExerciseClick: (ExerciseId) -> Unit = {},
    onControllerButtonClick: () -> Unit = {},
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.secondaryVariant)

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = { Text(text = "Noting Exercises") },
                backgroundColor = MaterialTheme.colors.secondary,
                actions = { ControllerButton(onControllerButtonClick = onControllerButtonClick) }
            )
        },
    ) { padding ->
        ExerciseList(
            exercises = exercises,
            modifier = Modifier.padding(padding),
            onClick = onExerciseClick,
        )
    }

}


@Composable
fun ControllerButton(onControllerButtonClick: () -> Unit) {
    IconButton(onClick = onControllerButtonClick) {
        Icon(
            painter = painterResource(R.drawable.ic_round_videogame_asset_24),
            contentDescription = "Controller Setup",
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
