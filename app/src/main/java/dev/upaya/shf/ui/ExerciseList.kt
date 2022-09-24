package dev.upaya.shf.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.upaya.shf.exampleExercises
import dev.upaya.shf.ExerciseConfig
import dev.upaya.shf.SHFViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList(exerciseConfigs: List<ExerciseConfig>) {

    val viewModel: SHFViewModel = viewModel()

    Surface {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(exerciseConfigs) { cfg ->
                ExerciseEntry(cfg) {
                    viewModel.activateLabelMap(cfg.labelMap)
                }
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
