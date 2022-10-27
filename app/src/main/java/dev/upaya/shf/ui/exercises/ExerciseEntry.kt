package dev.upaya.shf.ui.exercises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.exercises.exerciselist.ExerciseConfig
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseEntry(cfg: ExerciseConfig, onClick: (ExerciseConfig) -> Unit = {}) {

    Surface(
        elevation = 2.dp,
        modifier = Modifier.clickable { onClick(cfg) },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_round_self_improvement_24),
                contentDescription = "Meditating Person",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .padding(bottom = 4.dp)
                    .align(Alignment.CenterVertically),
            ) {

                Text(
                    text = cfg.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = cfg.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface.copy(alpha = .5f),
                )

            }

        }

    }

}


@Preview
@Composable
fun ExerciseEntryPreview() {
    SHFTheme(darkTheme = true) {
        ExerciseEntry(
            cfg = ExerciseConfig("Title", "Description"),
        )
    }
}
