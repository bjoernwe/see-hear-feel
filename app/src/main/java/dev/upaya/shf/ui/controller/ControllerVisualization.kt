package dev.upaya.shf.ui.controller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import dev.upaya.shf.R
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.inputs.KeyPressStates
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerVisualization(
    keyPressStates: KeyPressStates,
) {

    ConstraintLayout(
        modifier = Modifier
            .aspectRatio(1.2f)
    ) {

        ControllerImage()

        val guidelineAY = createGuidelineFromStart(.622f)
        val guidelineBX = createGuidelineFromTop(.416f)

        ControllerButton(
            verticalCenterGuideline = createGuidelineFromTop(.332f),
            horizontalCenterGuideline = guidelineAY,
            pressed = keyPressStates[InputKey.KEY_Y] != null,
        )

        ControllerButton(
            verticalCenterGuideline = guidelineBX,
            horizontalCenterGuideline = createGuidelineFromStart(.692f),
            pressed = keyPressStates[InputKey.KEY_B] != null,
        )

        ControllerButton(
            verticalCenterGuideline = guidelineBX,
            horizontalCenterGuideline = createGuidelineFromStart(.553f),
            pressed = keyPressStates[InputKey.KEY_X] != null,
        )

        ControllerButton(
            verticalCenterGuideline = createGuidelineFromTop(.499f),
            horizontalCenterGuideline = guidelineAY,
            pressed = keyPressStates[InputKey.KEY_A] != null,
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
        ControllerVisualization(
            keyPressStates = mutableMapOf()
        )
    }
}
