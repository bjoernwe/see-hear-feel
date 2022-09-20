package dev.upaya.shf.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import dev.upaya.shf.SessionActivity
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ExerciseEntry(title: String = "Title", description: String = "Description") {

    val context = LocalContext.current

    Surface(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                startActivity(context, Intent(context, SessionActivity::class.java), null)
            },
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
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
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
        ExerciseEntry()
    }
}
