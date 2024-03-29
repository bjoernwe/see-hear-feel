package dev.upaya.shf.data.labels

import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SHFLabelDataSource @Inject constructor(
    gamepadKeyEventDataSource: GamepadKeyEventDataSource,
) {
    val labelFlow: Flow<SHFLabelEvent> = gamepadKeyEventDataSource.inputKeyDown
        .drop(1)  // only emit the next actual event, not the current state
        .transform { keyEvent ->
        SHFLabelMap.getLabel(keyEvent.gamepadKey)?.let { label ->
            emit(SHFLabelEvent(label))
        }
    }
}
