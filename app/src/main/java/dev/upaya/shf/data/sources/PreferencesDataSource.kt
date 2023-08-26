package dev.upaya.shf.data.sources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.data.accessibility.AccessibilityPermissionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val prefKeyLockScreenSession = booleanPreferencesKey("lock_screen_session")


@Singleton
class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val appContext: Context,
    accessibilityPermissionSource: AccessibilityPermissionSource,
) {

    val isLockScreenPreferred: Flow<Boolean> = appContext
        .dataStore.data.map { prefs -> prefs[prefKeyLockScreenSession] ?: false }

    val isLockScreenSessionEnabled: Flow<Boolean> = accessibilityPermissionSource
        .isEnabled.combine(isLockScreenPreferred) { x, y -> x && y }

    suspend fun setLockScreenSessionPreference(enabled: Boolean) {
        appContext.dataStore.edit { settings ->
            settings[prefKeyLockScreenSession] = enabled
        }
    }

}
