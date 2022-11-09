package dev.upaya.shf.ui.controller

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.ConstraintLayoutScope
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ConstraintLayoutScope.ControllerButton(
    verticalCenterGuideline: ConstraintLayoutBaseScope.HorizontalAnchor,
    horizontalCenterGuideline: ConstraintLayoutBaseScope.VerticalAnchor,
    buttonKey: InputKey,
    inputKey: InputKey?,
    changeKey: Any? = null,
    activeColor: Color = MaterialTheme.colors.secondary,
    inactiveColor: Color = Color.Black,
    buttonSizeRatio: Float = .044f,
) {

    val buttonColor = remember { Animatable(Color.Black) }

    LaunchedEffect(changeKey) {
        if (inputKey == buttonKey) {
            buttonColor.snapTo(activeColor)
            buttonColor.animateTo(
                targetValue = inactiveColor,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
        }
    }

    Surface(
        color = buttonColor.value,
        modifier = Modifier
            .constrainAs(createRef()) {
                centerAround(horizontalCenterGuideline)
                centerAround(verticalCenterGuideline)
            }
            .fillMaxSize(buttonSizeRatio)
            .aspectRatio(1f)
            .clip(CircleShape)
    ) {
        // Button empty
    }

}


@Preview
@Composable
fun ControllerButtonPreview() {
    SHFTheme {
        ConstraintLayout {
            ControllerButton(
                horizontalCenterGuideline = createGuidelineFromStart(.5f),
                verticalCenterGuideline = createGuidelineFromTop(.5f),
                inputKey = InputKey.KEY_X,
                buttonKey = InputKey.KEY_X,
            )
        }
    }
}
