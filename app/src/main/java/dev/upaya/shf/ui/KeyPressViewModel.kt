package dev.upaya.shf.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.KeyPressRepository
import javax.inject.Inject


@HiltViewModel
class KeyPressViewModel @Inject constructor(
    private val keyPressRepository: KeyPressRepository,
) : ViewModel() {

    fun enableKeyCapturing() {
        keyPressRepository.enableKeyCapturing(true)
    }

    fun disableKeyCapturing() {
        keyPressRepository.enableKeyCapturing(false)
    }

}
