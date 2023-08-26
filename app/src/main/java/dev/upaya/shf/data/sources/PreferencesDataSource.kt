package dev.upaya.shf.data.sources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.accessibility.AccessibilityPermissionSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


@Singleton
class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
    accessibilityPermissionSource: AccessibilityPermissionSource,
    @ApplicationCoroutineScope private val externalScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private val prefKeyLockScreenSession = booleanPreferencesKey("lock_screen_session")

    private val _isLockScreenPreferred = MutableStateFlow(false)
    val isLockScreenPreferred: StateFlow<Boolean> = _isLockScreenPreferred

    private val _isLockScreenSessionEnabled = MutableStateFlow(false)
    val isLockScreenSessionEnabled: StateFlow<Boolean> = _isLockScreenSessionEnabled

    init {

        externalScope.launch(ioDispatcher) {
            appContext.dataStore.data
                .map { prefs -> prefs[prefKeyLockScreenSession] ?: false }
                .collect { pref -> _isLockScreenPreferred.value = pref }
        }

        externalScope.launch(ioDispatcher) {
            accessibilityPermissionSource.isEnabled
                .combine(isLockScreenPreferred) { x, y -> x && y }
                .collect { pref -> _isLockScreenSessionEnabled.value = pref }
        }
    }

    suspend fun setLockScreenSessionPreference(enabled: Boolean) {
        appContext.dataStore.edit { settings ->
            settings[prefKeyLockScreenSession] = enabled
        }
    }

}
