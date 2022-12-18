package dev.upaya.shf.ui

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.SHFActivity
import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.InputEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*


@Composable
internal fun SetStatusBarColor(color: Color = MaterialTheme.colors.background) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = color)
}


@Composable
internal fun KeepScreenOn() {
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


internal suspend fun MutableInteractionSource.simulatePress() {
    val press = PressInteraction.Press(Offset.Zero)
    this.emit(press)
    this.emit(PressInteraction.Release(press))
}


fun <T> Flow<T>.asSharedFlow(scope: CoroutineScope): SharedFlow<T> {
    return this.shareIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        replay = 0,
    )
}


fun SharedFlow<InputEvent>.transformToLabel(
    labelMap: LabelMap,
    scope: CoroutineScope
): SharedFlow<Label> {

    return this.transform { inputEvent ->
        emit(labelMap.getLabel(inputEvent.inputKey))
    }.shareIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        replay = 0,
    )

}


fun SharedFlow<InputEvent>.transformToLabel(
    labelMap: LabelMap,
    scope: CoroutineScope
): SharedFlow<Label> {

    return this.transform { inputEvent ->
        emit(labelMap.getLabel(inputEvent.inputKey))
    }.shareIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        replay = 0,
    )

}
