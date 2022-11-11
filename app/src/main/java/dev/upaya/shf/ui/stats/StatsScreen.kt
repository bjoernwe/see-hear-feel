package dev.upaya.shf.ui.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.upaya.shf.exercises.labels.labelFeel
import dev.upaya.shf.exercises.labels.labelHear
import dev.upaya.shf.exercises.labels.labelSee
import dev.upaya.shf.ui.input.LabelFreqs
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun StatsScreen(
    labelFreqs: LabelFreqs,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Statistics") },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
    ) { scaffoldPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
        ) {

            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colors.primarySurface,
                elevation = 4.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 2.dp)
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Labels".uppercase(),
                        fontWeight = Bold,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )

                    Spacer(modifier = Modifier.padding(vertical = 4.dp))

                    LabelFreqTable(
                        labelFreqs = labelFreqs,
                    )

                }

            }

        }
        
    }
    
}


@Preview
@Composable
fun StatsScreenPreview() {

    val labelFreqs: LabelFreqs = mutableMapOf(
        labelSee to 15,
        labelHear to 5,
        labelFeel to 1,
    )

    SHFTheme(darkTheme = true) {
        StatsScreen(
            labelFreqs = labelFreqs,
        )
    }
}
