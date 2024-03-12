package dev.upaya.shf.data.preferences

import javax.inject.Inject


class PreferencesRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {
    val isPacingEnabled = preferencesDataSource.isPacingEnabled

    fun setPacingPreference(enabled: Boolean) {
        preferencesDataSource.setPacingPreference(enabled = enabled)
    }
}
