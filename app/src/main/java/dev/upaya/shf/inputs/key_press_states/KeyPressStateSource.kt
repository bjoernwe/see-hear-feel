package dev.upaya.shf.inputs.key_press_states

import dev.upaya.shf.inputs.keys.InputKey
import dev.upaya.shf.inputs.keys.GlobalInputKeySource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<InputKey, Date>


@Singleton
class KeyPressStateSource @Inject constructor(
    private val inputKeySource: GlobalInputKeySource,
) {

    fun getKeyPressStates(scope: CoroutineScope): StateFlow<KeyPressStates> {

        val keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())

        scope.launch {
            inputKeySource.inputKeyDown.collect { inputKey ->
                keyPressStates.addStateFor(inputKey)
            }
        }

        scope.launch {
            inputKeySource.inputKeyUp.collect { inputKey ->
                keyPressStates.removeStateFor(inputKey)
            }
        }

        return keyPressStates
    }

}
