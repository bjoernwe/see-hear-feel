package dev.upaya.shf.ui.controller

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.upaya.shf.R
import dev.upaya.shf.inputs.InputEventSource
import dev.upaya.shf.inputs.InputKey
import dev.upaya.shf.ui.theme.SHFTheme


@Composable
fun ControllerVisualization(
    modifier: Modifier = Modifier,
    inputEventSource: InputEventSource,
) {

    val inputEvent by inputEventSource.inputEvent.collectAsState()

    Icon(
        painter = painterResource(R.drawable.stadia_controller_fill0_wght100_grad_25_opsz48),
        contentDescription = "Game Controller Outline",
        modifier = modifier
            .drawWithContent {

                val buttonRadius = this.size.minDimension / 35f
                val buttonColorInactive = Color.Black

                drawContent()

                drawCircle(
                    color = if (inputEvent?.inputKey == InputKey.KEY_Y) Color.Green else buttonColorInactive,
                    radius = buttonRadius,
                    center = this.center + Offset(
                        0.146f * this.size.minDimension,
                        -0.168f * this.size.minDimension,
                    )
                )
                drawCircle(
                    color = if (inputEvent?.inputKey == InputKey.KEY_X) Color.Green else buttonColorInactive,
                    radius = buttonRadius,
                    center = this.center + Offset(
                        0.063f * this.size.minDimension,
                        -0.084f * this.size.minDimension,
                    )
                )
                drawCircle(
                    color = if (inputEvent?.inputKey == InputKey.KEY_B) Color.Green else buttonColorInactive,
                    radius = buttonRadius,
                    center = this.center + Offset(
                        0.23f * this.size.minDimension,
                        -0.084f * this.size.minDimension,
                    )
                )
                drawCircle(
                    color = if (inputEvent?.inputKey == InputKey.KEY_A) Color.Green else buttonColorInactive,
                    radius = buttonRadius,
                    center = this.center + Offset(
                        0.146f * this.size.minDimension,
                        -0.001f * this.size.minDimension,
                    )
                )
            }
    )
}


@Preview
@Composable
fun ControllerVisualizationPreview() {
    val inputEventSource = InputEventSource()
    SHFTheme(darkTheme = true) {
        ControllerVisualization(
            modifier = Modifier.fillMaxSize(),
            inputEventSource = inputEventSource,
        )
    }
}
