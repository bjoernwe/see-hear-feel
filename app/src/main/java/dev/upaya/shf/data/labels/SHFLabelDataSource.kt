package dev.upaya.shf.data.labels

import dev.upaya.shf.data.gamepad.GamepadKey
import dev.upaya.shf.data.gamepad.GamepadKeyEventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SHFLabelDataSource @Inject constructor(
    gamepadKeyEventDataSource: GamepadKeyEventDataSource,
) {
    val labelFlow: Flow<SHFLabelEvent> = gamepadKeyEventDataSource.inputKeyDown
        .drop(1)  // only emit the next actual event, not the current state
        .map { it.gamepadKey }
        .mapNotNull(::toLabel)
        .map(::SHFLabelEvent)
}


private fun toLabel(key: GamepadKey): SHFLabel? = when(key) {
    GamepadKey.KEY_LEFT -> SHFLabel.SEE
    GamepadKey.KEY_RIGHT -> SHFLabel.HEAR
    GamepadKey.KEY_DOWN -> SHFLabel.FEEL
    GamepadKey.KEY_UP -> SHFLabel.GONE
    GamepadKey.KEY_A -> SHFLabel.SEE
    GamepadKey.KEY_B -> SHFLabel.HEAR
    GamepadKey.KEY_X -> SHFLabel.GONE
    GamepadKey.KEY_Y -> SHFLabel.FEEL
    else -> null
}
