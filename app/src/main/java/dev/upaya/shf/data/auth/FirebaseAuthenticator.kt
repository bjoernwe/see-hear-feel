package dev.upaya.shf.data.auth

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseAuthenticator @Inject constructor() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    internal suspend fun trySignIn(activityContext: Context): LogInStatus {

        if (isSignedIn()) {
            return getLogInStatus()
        }

        val googleCredential = signInWithGoogle(activityContext) ?: return getLogInStatus()

        firebaseAuth.signInWithGoogleCredential(googleCredential).await()

        return getLogInStatus()
    }

    internal suspend fun signOut(activityContext: Context): LogInStatus {
        firebaseAuth.signOut()
        signOutFromGoogle(activityContext)
        return getLogInStatus()
    }

    internal fun getUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }

    private fun isSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    internal fun getLogInStatus(): LogInStatus {
        return if (isSignedIn()) LogInStatus.LOGGED_IN else LogInStatus.LOGGED_OUT
    }
}
