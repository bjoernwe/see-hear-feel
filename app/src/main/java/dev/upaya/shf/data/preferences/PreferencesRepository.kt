package dev.upaya.shf.data.preferences

import javax.inject.Inject


class PreferencesRepository @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
    remoteConfigDataStore: RemoteConfigDataStore,
) {
    val isPacingEnabled = preferencesDataStore.isPacingEnabled
    val isLoginEnabled = remoteConfigDataStore.toggleLogin
    val isMindWanderingEnabled = remoteConfigDataStore.toggleMindWandering

    fun setPacingPreference(enabled: Boolean) {
        preferencesDataStore.setPacingPreference(enabled = enabled)
    }
}
