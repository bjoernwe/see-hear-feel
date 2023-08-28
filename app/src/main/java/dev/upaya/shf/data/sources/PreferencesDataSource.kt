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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val prefKeyLockScreenSession = booleanPreferencesKey("lock_screen_session")
private val prefKeyPacingEnabled = booleanPreferencesKey("pacing_enabled")


@Singleton
class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @ApplicationCoroutineScope private val externalScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    accessibilityPermissionSource: AccessibilityPermissionSource,
) {

    val isLockScreenPreferred: Flow<Boolean> = appContext
        .dataStore.data.map { prefs -> prefs[prefKeyLockScreenSession] ?: false }

    val isLockScreenSessionEnabled: Flow<Boolean> = accessibilityPermissionSource
        .isEnabled.combine(isLockScreenPreferred) { x, y -> x && y }

    val isPacingEnabled: Flow<Boolean> = appContext
        .dataStore.data.map { prefs -> prefs[prefKeyPacingEnabled] ?: false }

    fun setLockScreenSessionPreference(enabled: Boolean) {
        externalScope.launch(ioDispatcher) { // make function main-safe
            appContext.dataStore.edit { settings ->
                settings[prefKeyLockScreenSession] = enabled
            }
        }
    }

    fun setPacingPreference(enabled: Boolean) {
        externalScope.launch(ioDispatcher) { // make function main-safe
            appContext.dataStore.edit { settings ->
                settings[prefKeyPacingEnabled] = enabled
            }
        }
    }

}
