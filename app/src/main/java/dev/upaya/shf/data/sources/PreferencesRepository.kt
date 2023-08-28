package dev.upaya.shf.data.sources

import javax.inject.Inject


class PreferencesRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {
    val isLockScreenPreferred = preferencesDataSource.isLockScreenPreferred
    val isLockScreenSessionEnabled = preferencesDataSource.isLockScreenSessionEnabled
    val isPacingEnabled = preferencesDataSource.isPacingEnabled

    fun setLockScreenSessionPreference(enabled: Boolean) {
        preferencesDataSource.setLockScreenSessionPreference(enabled = enabled)
    }

    fun setPacingPreference(enabled: Boolean) {
        preferencesDataSource.setPacingPreference(enabled = enabled)
    }
}
