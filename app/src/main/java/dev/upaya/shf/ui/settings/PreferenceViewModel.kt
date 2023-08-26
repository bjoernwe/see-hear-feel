package dev.upaya.shf.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.sources.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) : ViewModel() {

    val isLockScreenPreferred: Flow<Boolean> = preferencesDataSource.isLockScreenPreferred
    //val isLockScreenSessionEnabled: StateFlow<Boolean> = preferenceSource.isLockScreenSessionEnabled

    suspend fun setLockScreenPreference(enableLockScreenSession: Boolean) {
        preferencesDataSource.setLockScreenSessionPreference(enabled = enableLockScreenSession)
    }

}
