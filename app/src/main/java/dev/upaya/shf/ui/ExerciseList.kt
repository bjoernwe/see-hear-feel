package dev.upaya.shf.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.upaya.shf.exercises.ExerciseConfig
import dev.upaya.shf.exercises.ExerciseViewModel
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseList(onClick: (ExerciseConfig) -> Unit = {}) {

    val vm: ExerciseViewModel = viewModel()
    val exercises by vm.exercises.collectAsState()

    Surface {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(exercises) { cfg ->
                ExerciseEntry(cfg = cfg, onClick = onClick)
            }
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
