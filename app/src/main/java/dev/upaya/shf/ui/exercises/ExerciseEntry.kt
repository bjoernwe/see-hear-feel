package dev.upaya.shf.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick(cfg) },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_round_self_improvement_24),
                contentDescription = null,
                Modifier
                    .size(64.dp)
                    .padding(4.dp),
            )
            
            Spacer(modifier = Modifier.width(4.dp))

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
                    color = MaterialTheme.colors.secondary,
                )

                Spacer(modifier = Modifier.height(4.dp))

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
    SHFTheme {
        ExerciseEntry(ExerciseConfig("Title", "Description"))
    }
}
