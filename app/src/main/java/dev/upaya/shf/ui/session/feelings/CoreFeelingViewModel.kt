package dev.upaya.shf.ui.session.feelings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.exercises.feelings.CoreFeelingsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CoreFeelingViewModel @Inject constructor(
    private val coreFeelingsRepository: CoreFeelingsRepository,
) : ViewModel() {

    val currentCoreFeeling: StateFlow<String?> = coreFeelingsRepository.currentFeeling
    val resultList = coreFeelingsRepository.resultList
    val round: StateFlow<Int> = coreFeelingsRepository.round

    fun keepCurrentFeeling() {
        coreFeelingsRepository.keepCurrentFeeling()
    }

    fun discardCurrentFeeling() {
        coreFeelingsRepository.discardCurrentFeeling()
    }

}
