package dev.upaya.shf.background

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class AccessibilitySettingViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
) : ViewModel() {

    private val accessibilitySettingSource = AccessibilitySettingSource(appContext = appContext)

    val backgroundServiceAvailability: StateFlow<Boolean> = accessibilitySettingSource.backgroundServiceAvailability

    fun updateAvailability() {
        accessibilitySettingSource.updateAvailability()
    }

}
