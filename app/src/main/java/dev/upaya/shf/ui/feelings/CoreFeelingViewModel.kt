package dev.upaya.shf.ui.feelings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.feelings.CoreFeelingsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CoreFeelingViewModel @Inject constructor() : ViewModel() {

    private val coreFeelingsRepository = CoreFeelingsRepository()
    val currentCoreFeeling: StateFlow<String?> = coreFeelingsRepository.currentFeeling
    val resultList = coreFeelingsRepository.resultList

    fun keepCurrentFeeling() {
        coreFeelingsRepository.keepCurrentFeeling()
    }

    fun discardCurrentFeeling() {
        coreFeelingsRepository.discardCurrentFeeling()
    }

}
