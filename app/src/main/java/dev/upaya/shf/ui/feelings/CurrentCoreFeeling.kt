package dev.upaya.shf.ui.feelings

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.ui.session.FadingText
import dev.upaya.shf.ui.varelaFontFamily


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurrentCoreFeeling(
    currentCoreFeeling: String?,
    inputEvent: InputEvent?,
    label: Label?
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "\"I feel ...\"",
            fontSize = 20.sp,
            fontFamily = varelaFontFamily,
            color = MaterialTheme.colors.secondary,
        )

        AnimatedContent(
            targetState = currentCoreFeeling,
        ) { targetCoreFeeling ->
            Text(
                text = targetCoreFeeling?.uppercase() ?: "",
                fontSize = 40.sp,
                fontFamily = varelaFontFamily,
                color = MaterialTheme.colors.secondary,
            )
        }

        FadingText(
            text = "(${(label?.primary ?: "").lowercase()})",
            key = inputEvent,
            fontSize = 15.sp,
        )
    }

}
