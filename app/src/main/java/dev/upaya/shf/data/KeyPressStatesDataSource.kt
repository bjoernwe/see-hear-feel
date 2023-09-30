package dev.upaya.shf.data

import dev.upaya.shf.data.sources.InputKey
import dev.upaya.shf.data.sources.KeyPressDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton


typealias KeyPressStates = Map<InputKey, Date>


@Singleton
class KeyPressStatesDataSource @Inject constructor(
    private val keyPressDataSource: KeyPressDataSource,
) {

    fun getKeyPressStates(scope: CoroutineScope): StateFlow<KeyPressStates> {

        val keyPressStates: MutableStateFlow<KeyPressStates> = MutableStateFlow(mapOf())

        // TODO: Launch off the main thread
        scope.launch {
            keyPressDataSource.inputKeyDown.collect { inputKey ->
                keyPressStates.addStateFor(inputKey)
            }
        }

        scope.launch {
            keyPressDataSource.inputKeyUp.collect { inputKey ->
                keyPressStates.removeStateFor(inputKey)
            }
        }

        return keyPressStates
    }

}
