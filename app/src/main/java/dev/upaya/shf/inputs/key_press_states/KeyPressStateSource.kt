package dev.upaya.shf.inputs.key_press_states

import dev.upaya.shf.inputs.input_keys.InputKey
import dev.upaya.shf.inputs.input_keys.InputKeySource
import dev.upaya.shf.utils.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
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
    inputKeySource: InputKeySource,
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
) {

    private val _keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())
    val keyPressStates: StateFlow<KeyPressStates> = _keyPressStates

    init {

        val scope = CoroutineScope(dispatcher)

        scope.launch {
            inputKeySource.inputKeyDown.collect { inputKey ->
                _keyPressStates.addStateFor(inputKey)
            }
        }

        scope.launch {
            inputKeySource.inputKeyUp.collect { inputKey ->
                _keyPressStates.removeStateFor(inputKey)
            }
        }

    }

}
