package dev.upaya.shf.inputs

import dev.upaya.shf.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton


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
                _keyPressStates.value = _keyPressStates.value
                    .toMutableMap()
                    .apply { this[inputKey] = Date() }
                    .toMap()
            }
        }

        scope.launch {
            inputKeySource.inputKeyUp.collect { inputKey ->
                _keyPressStates.value = _keyPressStates.value
                    .toMutableMap()
                    .apply { remove(inputKey) }
                    .toMap()
            }
        }

    }

}