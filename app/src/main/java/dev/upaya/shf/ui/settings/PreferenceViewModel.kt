package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.inputs.preferences.PreferenceSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val preferenceSource: PreferenceSource,
) : ViewModel() {

    val isLockScreenPreferred: StateFlow<Boolean> = preferenceSource.isLockScreenPreferred
    //val isLockScreenSessionEnabled: StateFlow<Boolean> = preferenceSource.isLockScreenSessionEnabled

    suspend fun setLockScreenPreference(enableLockScreenSession: Boolean) {
        preferenceSource.setLockScreenSessionPreference(enabled = enableLockScreenSession)
    }

}
