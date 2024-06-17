package dev.upaya.shf.data.preferences

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


private const val TOGGLE_LOGIN = "toggle_login"
private const val TOGGLE_MIND_WANDERING = "toggle_mind_wandering"


@Singleton
class RemoteConfigDataStore @Inject constructor() {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 60 //* 60
    }

    private val _toggleLogin: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val toggleLogin: StateFlow<Boolean> = _toggleLogin

    private val _toggleMindWandering: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val toggleMindWandering: StateFlow<Boolean> = _toggleMindWandering

    private val configToFlowMap = mapOf(
        TOGGLE_LOGIN to _toggleLogin,
        TOGGLE_MIND_WANDERING to _toggleMindWandering,
    )

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        loadRemoteConfig()
    }

    fun loadRemoteConfig() {
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            configToFlowMap.forEach { (key, flow) ->
                flow.value = remoteConfig.getBoolean(key)
                Timber.d("RemoteConfig: $key -> ${remoteConfig.getBoolean(key)}")
            }
        }
    }
}
