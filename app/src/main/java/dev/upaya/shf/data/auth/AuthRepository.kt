package dev.upaya.shf.data.auth

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuthenticator: FirebaseAuthenticator,
) {

    private val _logInStatus = MutableStateFlow(firebaseAuthenticator.getLogInStatus())
    val logInStatus: StateFlow<LogInStatus> = _logInStatus

    private val _userEmail = MutableStateFlow(firebaseAuthenticator.getUserEmail())
    val userEmail: StateFlow<String?> = _userEmail

    suspend fun signIn(activityContext: Context) {
        _logInStatus.value = LogInStatus.LOGGING_IN
        _logInStatus.value = firebaseAuthenticator.trySignIn(activityContext)
        updateUserInfoFlows()
    }

    suspend fun signOut(activityContext: Context) {
        _logInStatus.value = LogInStatus.LOGGING_OUT
        _logInStatus.value = firebaseAuthenticator.signOut(activityContext)
        updateUserInfoFlows()
    }

    private fun updateUserInfoFlows() {
        _userEmail.value = firebaseAuthenticator.getUserEmail()
    }
}
