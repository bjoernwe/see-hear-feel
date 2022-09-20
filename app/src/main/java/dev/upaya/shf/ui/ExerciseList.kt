package dev.upaya.shf.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList() {
    Column(modifier = Modifier.fillMaxWidth()) {
        ExerciseEntry(
            title = "See / Hear / Feel (steady)",
            description = "Note SHF while keeping a steady pace"
        )
        ExerciseEntry(
            title = "See / Hear / Feel (deep)",
            description = "Note SHF with a focus on really \"soaking in\"",
        )
        ExerciseEntry(
            title = "Pleasant / Unpleasant / Neutral",
            description = "Note the valence of sensations",
        )
        ExerciseEntry(
            title = "Mahasi Sayadaw - Basic I",
            description = "Note \"rising\" and \"falling\" for each breath",
        )
        ExerciseEntry(
            title = "Mahasi Sayadaw - Basic II",
            description = "Like \"Basic I\" plus noting every distraction",
        )
    }
}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme {
        ExerciseList()
    }
}
