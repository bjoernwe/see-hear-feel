package dev.upaya.shf.data.gamepad_input

import dev.upaya.shf.data.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<GamepadKey, Date>


@Singleton
class KeyPressStatesDataSource @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    fun getKeyPressStates(scope: CoroutineScope): StateFlow<KeyPressStates> {

        val keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())

        scope.launch(defaultDispatcher) {
            keyPressDataSource.inputKeyDown.collect { inputEvent ->
                keyPressStates.addStateFor(inputEvent.gamepadKey)
            }
        }

        scope.launch(defaultDispatcher) {
            keyPressDataSource.inputKeyUp.collect { inputEvent ->
                keyPressStates.removeStateFor(inputEvent.gamepadKey)
            }
        }

        return keyPressStates
    }

}
