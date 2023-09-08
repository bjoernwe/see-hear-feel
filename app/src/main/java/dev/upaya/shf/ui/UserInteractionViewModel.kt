package dev.upaya.shf.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.UserInteractionRepository
import javax.inject.Inject


@HiltViewModel
class UserInteractionViewModel @Inject constructor(
    private val userInteractionRepository: UserInteractionRepository,
) : ViewModel() {

    fun enableKeyCapturing() {
        userInteractionRepository.enableKeyLogging(true)
    }

    fun disableKeyCapturing() {
        userInteractionRepository.enableKeyLogging(false)
    }

}
