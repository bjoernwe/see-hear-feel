package dev.upaya.shf.data.auth

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor() {

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail

    suspend fun signIn(activityContext: Context) {
        val credential = signInWithGoogle(activityContext)
        _userEmail.value = credential?.id
    }
}
