package dev.upaya.shf.ui.controller

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.ConstraintLayoutScope
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ConstraintLayoutScope.ControllerButton(
    verticalCenterGuideline: ConstraintLayoutBaseScope.HorizontalAnchor,
    horizontalCenterGuideline: ConstraintLayoutBaseScope.VerticalAnchor,
    pressed: Boolean,
    activeColor: Color = Color.White,
    inactiveColor: Color = MaterialTheme.colors.secondaryVariant,
    buttonSizeRatio: Float = .044f,
    aspectRatio: Float = 1f,
    shape: Shape = CircleShape,
) {

    Surface(
        color = if (pressed) activeColor else inactiveColor,
        modifier = Modifier
            .constrainAs(createRef()) {
                centerAround(horizontalCenterGuideline)
                centerAround(verticalCenterGuideline)
            }
            .fillMaxSize(buttonSizeRatio)
            .aspectRatio(aspectRatio)
            .clip(shape)
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
                pressed = false,
            )
        }
    }
}
