package dev.upaya.shf.data.gamepad

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
class GamepadPressStateDataSource @Inject constructor(
    private val gamepadKeyEventDataSource: GamepadKeyEventDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    fun getKeyPressStates(scope: CoroutineScope): StateFlow<KeyPressStates> {

        val keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())

        scope.launch(defaultDispatcher) {
            gamepadKeyEventDataSource.inputKeyDown.collect { inputEvent ->
                keyPressStates.addStateFor(inputEvent.gamepadKey)
            }
        }

        scope.launch(defaultDispatcher) {
            gamepadKeyEventDataSource.inputKeyUp.collect { inputEvent ->
                keyPressStates.removeStateFor(inputEvent.gamepadKey)
            }
        }

        return keyPressStates
    }

}
