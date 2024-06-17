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

    private val _userEmail = MutableStateFlow<String?>(null)
    val userEmail: StateFlow<String?> = _userEmail

    init {
        firebaseAuth.currentUser?.also { user -> _userEmail.value = user.email }
    }

    suspend fun signIn(activityContext: Context) {

        if (firebaseAuth.currentUser != null)
            return

        val googleCredential = signInWithGoogle(activityContext) ?: return

        firebaseAuth.signInWithGoogleCredential(googleCredential)
            .addOnSuccessListener {
                _userEmail.value = it.user?.email
            }
    }

    suspend fun signOut(activityContext: Context) {
        firebaseAuth.signOut()
        _userEmail.value = firebaseAuth.currentUser?.email
        signOutFromGoogle(activityContext)
    }
}
