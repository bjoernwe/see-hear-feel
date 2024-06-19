package dev.upaya.shf.data.auth

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _logInStatus = MutableStateFlow(if (isFirebaseSignedIn()) LogInStatus.LOGGED_IN else LogInStatus.LOGGED_OUT)
    val logInStatus: StateFlow<LogInStatus> = _logInStatus

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail

    init {
        firebaseAuth.currentUser?.also { user -> _userEmail.value = user.email }
    }

    suspend fun signIn(activityContext: Context) {

        if (_logInStatus.value != LogInStatus.LOGGED_OUT)
            return

        _logInStatus.value = LogInStatus.LOGGING_IN
        val googleCredential = signInWithGoogle(activityContext) ?: return

        firebaseAuth.signInWithGoogleCredential(googleCredential)
            .addOnSuccessListener {
                _userEmail.value = it.user?.email
                _logInStatus.value = LogInStatus.LOGGED_IN
            }
            .addOnFailureListener {
                _logInStatus.value = LogInStatus.LOGGED_OUT
            }
    }

    suspend fun signOut(activityContext: Context) {
        _logInStatus.value = LogInStatus.LOGGING_OUT
        firebaseAuth.signOut()
        _userEmail.value = firebaseAuth.currentUser?.email
        signOutFromGoogle(activityContext)
        _logInStatus.value = LogInStatus.LOGGED_OUT
    }

    private fun isFirebaseSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
