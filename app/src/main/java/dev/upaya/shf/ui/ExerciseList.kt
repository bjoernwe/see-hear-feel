package dev.upaya.shf.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.ExerciseConfig
import dev.upaya.shf.exampleExercises

import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList(exerciseConfigs: List<ExerciseConfig>) {
    Surface {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(exerciseConfigs) { cfg ->
                ExerciseEntry(cfg.title, cfg.description)
            }
        }
    }
}


@Preview
@Composable
fun ExerciseListPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseList(exampleExercises)
    }
}
