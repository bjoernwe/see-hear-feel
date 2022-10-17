package dev.upaya.shf.feelings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


private val FEELINGS = listOf(
    "inadequate", "empty (loss)", "insignificant", "bad (person)",
    "hopeless", "helpless", "lost", "alone", "worthless"
)


class CoreFeelingsRepository {

    private val urnList = UrnList(initial = FEELINGS)

    private val _currentFeeling = MutableStateFlow(urnList.getCurrent())
    val currentFeeling: StateFlow<String?> = _currentFeeling

    private val _finalList = MutableStateFlow<List<String>>(emptyList())
    val finalList: StateFlow<List<String>> = _finalList

    fun keepCurrentFeeling() {
        _currentFeeling.value = urnList.getNext()
    }

    fun discardCurrentFeeling() {

        urnList.getCurrent()?.let { current ->
            _finalList.value = listOf(current) + _finalList.value
        }

        urnList.removeCurrent()

        _currentFeeling.value = urnList.getCurrent()
    }

}
