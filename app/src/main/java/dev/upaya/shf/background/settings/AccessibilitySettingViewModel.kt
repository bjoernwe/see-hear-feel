package dev.upaya.shf.background.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class AccessibilitySettingViewModel @Inject constructor(
    private val accessibilitySettingSource: AccessibilitySettingSource
) : ViewModel() {

    val backgroundServiceAvailability: StateFlow<Boolean> = accessibilitySettingSource.backgroundServiceAvailability

    fun updateAvailability() {
        accessibilitySettingSource.updateAvailability()
    }

}
