package dev.upaya.shf.data.labels

import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SHFLabelDataSource @Inject constructor(
    gamepadKeyEventDataSource: GamepadKeyEventDataSource,
) {
    val labelFlow: Flow<SHFLabelEvent> = gamepadKeyEventDataSource.inputKeyDown.transform { keyEvent ->
        SHFLabelMap.getLabel(keyEvent.gamepadKey)?.let { label ->
            emit(SHFLabelEvent(label))
        }
    }
}
