package dev.upaya.shf.ui.controller.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import dev.upaya.shf.R
import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.KeyPressStates
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerVisualization(
    keyPressStates: KeyPressStates,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(.9f),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .aspectRatio(1.2f)
        ) {
            ControllerImage()
            ControllerCross(keyPressStates = keyPressStates)
            ButtonsABXY(keyPressStates = keyPressStates)
            ButtonsLR(keyPressStates = keyPressStates)
        }
    }

}


@Composable
fun ControllerImage(
    tintColor: Color = MaterialTheme.colors.secondaryVariant,
) {
    Image(
        painter = painterResource(R.drawable.stadia_controller_fill0_wght100_grad_25_opsz48),
        contentDescription = "Game Controller",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color = tintColor),
        modifier = Modifier
            .aspectRatio(ratio = 1.2f)
            .fillMaxSize()
    )
}


@Composable
fun ConstraintLayoutScope.ControllerCross(
    keyPressStates: KeyPressStates,
) {
    val guidelineHorizontalCross = createGuidelineFromTop(.415f)
    val guidelineVerticalCross = createGuidelineFromStart(.38f)

    ControllerButton(
        verticalCenterGuideline = guidelineHorizontalCross,
        horizontalCenterGuideline = createGuidelineFromStart(.3374f),
        pressed = keyPressStates[InputKey.KEY_LEFT] != null,
        buttonSizeRatio = .062f,
        aspectRatio = 2.7f,
        shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),
    )

    ControllerButton(
        verticalCenterGuideline = guidelineHorizontalCross,
        horizontalCenterGuideline = createGuidelineFromStart(.421f),
        pressed = keyPressStates[InputKey.KEY_RIGHT] != null,
        buttonSizeRatio = .062f,
        aspectRatio = 2.7f,
        shape = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50),
    )

    ControllerButton(
        verticalCenterGuideline = createGuidelineFromTop(.3639f),
        horizontalCenterGuideline = guidelineVerticalCross,
        pressed = keyPressStates[InputKey.KEY_UP] != null,
        buttonSizeRatio = .022f,
        aspectRatio = .37f,
        shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50),
    )

    ControllerButton(
        verticalCenterGuideline = createGuidelineFromTop(.464f),
        horizontalCenterGuideline = guidelineVerticalCross,
        pressed = keyPressStates[InputKey.KEY_DOWN] != null,
        buttonSizeRatio = .022f,
        aspectRatio = .37f,
        shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50),
    )

}


@Composable
fun ConstraintLayoutScope.ButtonsABXY(
    keyPressStates: KeyPressStates,
) {
    
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


@Composable
fun ConstraintLayoutScope.ButtonsLR(
    keyPressStates: KeyPressStates,
) {

    val guidelineLR1 = createGuidelineFromTop(.15f)
    val guidelineLR2 = createGuidelineFromTop(.1f)
    val guidelineL = createGuidelineFromStart(.37f)
    val guidelineR = createGuidelineFromStart(.63f)

    ControllerButton(
        verticalCenterGuideline = guidelineLR1,
        horizontalCenterGuideline = guidelineL,
        pressed = keyPressStates[InputKey.KEY_L1] != null,
        buttonSizeRatio = .1f,
        aspectRatio = 5f,
        shape = RoundedCornerShape(50),
    )

    ControllerButton(
        verticalCenterGuideline = guidelineLR2,
        horizontalCenterGuideline = guidelineL,
        pressed = keyPressStates[InputKey.KEY_L2] != null,
        buttonSizeRatio = .07f,
        aspectRatio = 3.5f,
        shape = RoundedCornerShape(50),
    )

    ControllerButton(
        verticalCenterGuideline = guidelineLR1,
        horizontalCenterGuideline = guidelineR,
        pressed = keyPressStates[InputKey.KEY_R1] != null,
        buttonSizeRatio = .1f,
        aspectRatio = 5f,
        shape = RoundedCornerShape(50),
    )

    ControllerButton(
        verticalCenterGuideline = guidelineLR2,
        horizontalCenterGuideline = guidelineR,
        pressed = keyPressStates[InputKey.KEY_R2] != null,
        buttonSizeRatio = .07f,
        aspectRatio = 3.5f,
        shape = RoundedCornerShape(50),
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
