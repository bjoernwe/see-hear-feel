package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.R
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.exercises.exerciselist.exampleExercises
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseListScreen(
    exercises: List<ExerciseConfig>,
    onExerciseClick: (ExerciseConfig) -> Unit = {},
    onControllerButtonClick: () -> Unit = {},
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.secondaryVariant)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Noting Exercises") },
                backgroundColor = MaterialTheme.colors.secondary,
                actions = {
                    IconButton(onClick = onControllerButtonClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_videogame_asset_24),
                            contentDescription = "Game Controller",
                        )
                    }
                }
            )
        }
    ) { padding ->
        ExerciseList(
            exercises = exercises,
            modifier = Modifier.padding(padding),
            onClick = onExerciseClick,
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
