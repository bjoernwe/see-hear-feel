package dev.upaya.shf.data.preferences

import javax.inject.Inject


class PreferencesRepository @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) {
    val isPacingEnabled = preferencesDataStore.isPacingEnabled

    fun setPacingPreference(enabled: Boolean) {
        preferencesDataStore.setPacingPreference(enabled = enabled)
    }
}
