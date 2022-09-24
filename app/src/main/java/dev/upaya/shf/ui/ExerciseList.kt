package dev.upaya.shf.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList() {

    Surface {

        Column(modifier = Modifier.fillMaxWidth()) {

            ExerciseEntry(
                title = "SHF",
                description = "Standard See/Hear/Feel"
            )
            ExerciseEntry(
                title = "SHF (steady)",
                description = "See/Hear/Feel while keeping a steady pace"
            )
            ExerciseEntry(
                title = "SHF (deep)",
                description = "See/Hear/Feel with a focus on deeply \"soaking in\"",
            )
            ExerciseEntry(
                title = "Valence",
                description = "Pleasant / Unpleasant / Neutral",
            )
            ExerciseEntry(
                title = "Mahasi-Style - Basic I",
                description = "Rising/Falling for each breath",
            )
            ExerciseEntry(
                title = "Mahasi-Style - Basic II",
                description = "Like \"Basic I\" plus noting every distraction",
            )
            ExerciseEntry(
                title = "Do Nothing",
                description = "Any intention to control attention is let go of at soon as it is noticed",
            )
            ExerciseEntry(
                title = "Focus on Rest",
                description = "SHF with focus on inner and outer restful states",
            )

        }

    }

}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseList()
    }
}
