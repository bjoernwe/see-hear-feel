package dev.upaya.shf.ui.controller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.inputs.InputEventSource
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerVisualization(
    modifier: Modifier = Modifier,
    inputEventSource: InputEventSource? = null,
) {

    val inputEvent = inputEventSource?.inputEvent?.collectAsState()

    Image(
        painter = painterResource(R.drawable.stadia_controller_fill0_wght100_grad_25_opsz48),
        contentDescription = "Game Controller",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface.copy(alpha = .5f)),
        modifier = modifier
            .aspectRatio(ratio = 1.2f)
            .drawWithContent {
                drawContent()
                drawButtons(inputKey = inputEvent?.value?.inputKey)
            }
    )

}


private fun ContentDrawScope.drawButtons(
    inputKey: InputKey?,
) {
    drawButton(
        inputKey = inputKey,
        targetKey = InputKey.KEY_A,
    )
    drawButton(
        inputKey = inputKey,
        targetKey = InputKey.KEY_B,
    )
    drawButton(
        inputKey = inputKey,
        targetKey = InputKey.KEY_X,
    )
    drawButton(
        inputKey = inputKey,
        targetKey = InputKey.KEY_Y,
    )
}


private fun buttonOffset(inputKey: InputKey): Offset? {
    return when (inputKey) {
        InputKey.KEY_A -> Offset(0.146f, -0.001f)
        InputKey.KEY_B -> Offset(0.23f, -0.084f)
        InputKey.KEY_X -> Offset(0.063f, -0.084f)
        InputKey.KEY_Y -> Offset(0.146f, -0.168f)
        InputKey.KEY_UP -> null
        InputKey.KEY_DOWN -> null
        InputKey.KEY_LEFT -> null
        InputKey.KEY_RIGHT -> null
        InputKey.KEY_L1 -> null
        InputKey.KEY_R1 -> null
        InputKey.KEY_L2 -> null
        InputKey.KEY_R2 -> null
        InputKey.UNMAPPED -> null
    }
}


private fun ContentDrawScope.drawButton(
    inputKey: InputKey?,
    targetKey: InputKey,
    buttonRadiusDivider: Float = 35f,
    buttonColorActive: Color = Color.Green,
    buttonColorInactive: Color = Color.Black,
) {
    buttonOffset(targetKey)?.let { buttonOffset ->
        this.drawCircle(
            color = if (inputKey == targetKey) buttonColorActive else buttonColorInactive,
            radius = this.size.minDimension / buttonRadiusDivider,
            center = this.center + Offset(
                x = buttonOffset.x * this.size.minDimension,
                y = buttonOffset.y * this.size.minDimension,
            )
        )
    }
}


@Preview
@Composable
fun ControllerVisualizationPreview() {
    SHFTheme(darkTheme = true) {
        ControllerVisualization(
            modifier = Modifier.fillMaxSize(),
        )
    }
}