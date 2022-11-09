package dev.upaya.shf.ui.controller

import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import dev.upaya.shf.R
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun Controller(
    inputEvent: InputEvent?,
) {

    ConstraintLayout(
        modifier = Modifier
            .aspectRatio(1.2f)
    ) {

        ControllerImage()

        val colorAnimatableX = remember { Animatable(Color.Black) }

        ControllerButton(
            verticalCenterGuideline = createGuidelineFromTop(.416f),
            horizontalCenterGuideline = createGuidelineFromStart(.553f),
            inputKey = inputEvent?.inputKey,
            buttonKey = InputKey.KEY_X,
            changeKey = inputEvent,
            colorAnimatable = colorAnimatableX,
        )

    }
}


@Composable
fun ControllerImage() {
    Image(
        painter = painterResource(R.drawable.stadia_controller_fill0_wght100_grad_25_opsz48),
        contentDescription = "Game Controller",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface.copy(alpha = .5f)),
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .aspectRatio(ratio = 1.2f)
            .fillMaxSize()
    )
}


@Preview
@Composable
fun ControllerPreview() {
    SHFTheme(darkTheme = true) {
        Controller(
            inputEvent = null,
        )
    }
}
