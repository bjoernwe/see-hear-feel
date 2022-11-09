package dev.upaya.shf.ui.controller

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import dev.upaya.shf.R
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun Controller(key: Any? = null) {

    ConstraintLayout(
        modifier = Modifier
            .aspectRatio(1.2f)
    ) {

        ControllerImage()

        val buttonSizeRatio = .044f

        val guidelineHorizontalX = createGuidelineFromStart(.553f)
        val guidelineVerticalCenter = createGuidelineFromTop(.416f)

        val secondaryColor = MaterialTheme.colors.secondary
        val colorX = remember { Animatable(Color.Black) }

        LaunchedEffect(key) {
            colorX.snapTo(secondaryColor)
            colorX.animateTo(
                targetValue = Color.Black,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
        }

        Surface(
            color = colorX.value,
            modifier = Modifier
                .constrainAs(createRef()) {
                    centerAround(guidelineHorizontalX)
                    centerAround(guidelineVerticalCenter)
                }
                .fillMaxSize(buttonSizeRatio)
                .aspectRatio(1f)
                .clip(CircleShape)
        ) { }

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
        Controller()
    }
}
