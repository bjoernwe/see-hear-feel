package dev.upaya.shf.ui.session.feelings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.ui.session.FadingText
import dev.upaya.shf.ui.simulatePress
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun CurrentCoreFeeling(
    currentCoreFeeling: String?,
    inputEvent: InputEvent?,
    label: Label?,
    round: Int,
) {

    val interactionSource = remember { MutableInteractionSource() }.apply {
        LaunchedEffect(inputEvent) { simulatePress() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .clickable(
                onClick = {},
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = false, radius = 100.dp),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = "Round ${round+1}".uppercase(),
                    fontSize = 20.sp,
                    fontFamily = varelaFontFamily,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = "\"I feel ...\"",
                    fontSize = 20.sp,
                    fontFamily = varelaFontFamily,
                    color = MaterialTheme.colors.secondaryVariant,
                )
            }

       }

        Text(
            text = currentCoreFeeling?.uppercase() ?: "",
            fontSize = 40.sp,
            fontFamily = varelaFontFamily,
            color = MaterialTheme.colors.secondary,
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
        ) {
            FadingText(
                text = when (label)
                {
                    LabelMapKeepDiscard.labelKeep -> "strong resonance".uppercase()
                    LabelMapKeepDiscard.labelDiscard -> "weak resonance".uppercase()
                    else -> ""
                },
                modifier = Modifier.padding(bottom = 30.dp),
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 20.sp,
                key = inputEvent,
            )
        }

    }

}


@Preview
@Composable
fun CurrentCoreFeelingPreview() {
    SHFTheme(darkTheme = true) {
        CurrentCoreFeeling(
            currentCoreFeeling = "Core Feeling",
            inputEvent = null,
            label = LabelMapKeepDiscard.labelKeep,
            round = 0,
        )
    }
}
