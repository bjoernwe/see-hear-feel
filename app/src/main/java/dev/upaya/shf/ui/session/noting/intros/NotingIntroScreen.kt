package dev.upaya.shf.ui.session.noting.intros

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.ui.session.noting.intros.composables.TextWithUrl
import dev.upaya.shf.ui.session.noting.intros.composables.YouTubeImage
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingIntroScreen(
    exercise: ExerciseConfig,
    onClickButton: () -> Unit,
) {

    Column {

        Text(
            exercise.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
        )

        TextWithUrl {
            append(exercise.description)
        }

        YouTubeImage(videoId = "StBTuX0tqU8")

        Button(
            onClick = onClickButton,
        ) {
            Text("Start")
        }

    }

}


@Preview
@Composable
fun NotingIntroScreenPreview() {
    SHFTheme(darkTheme = true) {
        NotingIntroScreen(
            exercise = ExerciseConfig(
                title = "Title",
                description = "Description",
            ),
            onClickButton = {},
        )
    }
}
