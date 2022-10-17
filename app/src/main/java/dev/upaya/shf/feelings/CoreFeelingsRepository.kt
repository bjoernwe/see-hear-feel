package dev.upaya.shf.feelings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


private val FEELINGS = listOf("Core1", "Core2", "Core3")


class CoreFeelingsRepository {

    private var currentListIndex = 0
    private val currentList = FEELINGS.toMutableList()
    private val _currentFeeling = MutableStateFlow(currentList[currentListIndex])

    val currentFeeling: StateFlow<String> = _currentFeeling

    fun keepCurrentFeeling() {
        currentListIndex += 1
        currentListIndex %= currentList.size
        _currentFeeling.value = currentList[currentListIndex]
    }

    fun discardCurrentFeeling() {
        currentList.removeAt(currentListIndex)
        currentListIndex %= currentList.size
        _currentFeeling.value = currentList[currentListIndex]
    }

}
