package dev.upaya.shf.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_keys.InputKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*


@Composable
internal fun SetStatusBarColor(color: Color = MaterialTheme.colors.background) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = color)
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


fun SharedFlow<InputKey>.transformToLabel(
    labelMap: LabelMap,
    scope: CoroutineScope
): SharedFlow<Label> {

    return this.transform { inputKey ->
        emit(labelMap.getLabel(inputKey))
    }.shareIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        replay = 0,
    )

}
