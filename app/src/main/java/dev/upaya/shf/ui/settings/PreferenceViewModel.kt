package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    val isPacingEnabled: Flow<Boolean> = preferencesRepository.isPacingEnabled
    val isLogInEnabled: Flow<Boolean> = preferencesRepository.isLoginEnabled

    fun setPacingPreference(enabled: Boolean) {
        preferencesRepository.setPacingPreference(enabled = enabled)
    }

}
