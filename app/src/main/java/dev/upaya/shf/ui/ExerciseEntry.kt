package dev.upaya.shf.ui

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
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseEntry(title: String = "Title", description: String = "Description") {

    Surface(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(5.dp),
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)

            Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {

                Text(title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                Text(description, style = MaterialTheme.typography.body1)

            }

        }

    }

}


@Preview
@Composable
fun ExerciseEntryPreview() {
    SHFTheme {
        ExerciseEntry()
    }
}
