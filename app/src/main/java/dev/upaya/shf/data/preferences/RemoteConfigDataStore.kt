package dev.upaya.shf.data.preferences

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dev.upaya.shf.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


private const val TOGGLE_MIND_WANDERING = "toggle_mind_wandering"


@Singleton
class RemoteConfigDataStore @Inject constructor() {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 60 * 60
    }

    private val _toggleMindWandering: MutableStateFlow<Boolean> = MutableStateFlow(remoteConfig.getBoolean(TOGGLE_MIND_WANDERING))
    val toggleMindWandering: StateFlow<Boolean> = _toggleMindWandering

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        loadRemoteConfig()
    }

    fun loadRemoteConfig() {
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            _toggleMindWandering.value = remoteConfig.getBoolean(TOGGLE_MIND_WANDERING)
        }
    }
}
