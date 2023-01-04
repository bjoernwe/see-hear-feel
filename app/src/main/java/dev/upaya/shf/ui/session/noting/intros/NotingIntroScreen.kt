package dev.upaya.shf.ui.session.noting.intros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.session.noting.intros.composables.TextWithUrl
import dev.upaya.shf.ui.session.noting.intros.composables.YouTubeImage
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun NotingIntroScreen(
    exercise: ExerciseConfig,
    onClickButton: () -> Unit,
) {

    SetStatusBarColor()

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {

            Text(
                text = exercise.title,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
            )

            TextWithUrl {
                append(exercise.descriptionShort)
            }

            YouTubeImage(
                videoId = "StBTuX0tqU8",
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 18.dp)
            )

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = onClickButton,
        ) {
            Text(
                "Start".uppercase(),
                color = MaterialTheme.colors.onPrimary,
            )
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
                descriptionShort = "Description",
            ),
            onClickButton = {},
        )
    }
}
