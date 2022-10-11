package dev.upaya.shf.ui.session

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.SHFActivity
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun SessionScreen(
    viewModel: SessionViewModel = viewModel(),
) {

    SetStatusBarColor()
    KeepScreenOn()

    val inputEvent by viewModel.inputEvent.collectAsState()
    val label: String by viewModel.label.collectAsState(initial = "")

    // Simulate a key press on value change
    val interactionSource = remember { MutableInteractionSource() }.apply {
        LaunchedEffect(inputEvent) { simulatePress() }
    }

    Column(
        Modifier
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
        FadingText(text = label, key = inputEvent, fontFamily = varelaFontFamily)
    }

}


@Preview
@Composable
fun MainContentPreview() {
    SHFTheme(darkTheme = true) { SessionScreen() }
}


private suspend fun MutableInteractionSource.simulatePress() {
    val press = PressInteraction.Press(Offset.Zero)
    this.emit(press)
    this.emit(PressInteraction.Release(press))
}


@Composable
private fun SetStatusBarColor(color: Color = MaterialTheme.colors.background) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = color)
}


@Composable
private fun KeepScreenOn() {
    val context = LocalContext.current
    DisposableEffect(context) {
        context.getActivity()?.setKeepScreenOn()
        onDispose {
            context.getActivity()?.clearKeepScreenOn()
        }
    }
}

private fun Context.getActivity(): SHFActivity? = when (this) {
    is SHFActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
