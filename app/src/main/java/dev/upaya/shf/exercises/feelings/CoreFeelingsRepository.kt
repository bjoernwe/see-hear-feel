package dev.upaya.shf.exercises.feelings

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

    private val _round = MutableStateFlow(urnList.getRound())
    val round: StateFlow<Int> = _round

    private val _resultList = MutableStateFlow<List<String>>(emptyList())
    val resultList: StateFlow<List<String>> = _resultList

    fun keepCurrentFeeling() {
        urnList.keepCurrent()
        updateUrnFlows()
    }

    fun discardCurrentFeeling() {
        addCurrentFeelingToResultList()
        urnList.removeCurrent()
        updateUrnFlows()
    }

    private fun addCurrentFeelingToResultList() {
        urnList.getCurrent()?.let { current ->
            if (round.value > 0) {
                _resultList.value = listOf(current) + _resultList.value
            }
        }
    }

    private fun updateUrnFlows() {
        _currentFeeling.value = urnList.getCurrent()
        _round.value = urnList.getRound()
    }

}
