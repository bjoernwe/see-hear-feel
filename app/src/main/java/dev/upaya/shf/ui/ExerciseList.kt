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
        ExerciseEntry()
        ExerciseEntry()
        ExerciseEntry()
    }
}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme {
        ExerciseList()
    }
}
