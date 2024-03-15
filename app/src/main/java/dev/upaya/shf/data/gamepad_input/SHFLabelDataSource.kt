package dev.upaya.shf.data.gamepad_input

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SHFLabelDataSource @Inject constructor(
    keyPressDataSource: KeyPressDataSource,
) {
    val labelFlow: Flow<SHFLabelEvent> = keyPressDataSource.inputKeyDown.transform { keyEvent ->
        SHFLabelMap.getLabel(keyEvent.gamepadKey)?.let { label ->
            emit(SHFLabelEvent(label))
        }
    }
}
