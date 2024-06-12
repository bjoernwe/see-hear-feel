package dev.upaya.shf.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialOption
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber
import java.security.SecureRandom


// This client ID can be found in the Cloud Console under API -> Credentials and there it is the
// client ID of a web application
const val WEB_CLIENT_ID = "993544001537-l5l3ehg0j5h7jus693fhqvmq5smiu88g.apps.googleusercontent.com"


internal fun FirebaseAuth.signInWithGoogleCredential(credential: GoogleIdTokenCredential): Task<AuthResult> {
    return this.signInWithCredential(
        GoogleAuthProvider.getCredential(credential.idToken, null)
    )
}


internal suspend fun signInWithGoogle(activityContext: Context): GoogleIdTokenCredential? {
    val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption
        .Builder(serverClientId = WEB_CLIENT_ID)
        .setNonce(generateNonce())
        .build()
    return signInWithCredentialManager(signInWithGoogleOption, activityContext)
}


private suspend fun signInWithCredentialManager(
    option: CredentialOption,
    activityContext: Context,
): GoogleIdTokenCredential? {

    try {

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(option)
            .build()

        val credentialResponse = CredentialManager.create(activityContext)
            .getCredential(activityContext, request)

        return GoogleIdTokenCredential.createFrom(credentialResponse.credential.data)

    } catch (e: GetCredentialCancellationException) {
        Timber.tag("Authentication").w(e, "Authentication canceled")
        return null
    } catch (e: NoCredentialException) {
        Timber.tag("Authentication").w(e, "No credential found")
        return null
    }
}


internal fun generateNonce(size: Int = 50): String {
    return ByteArray(size).also {
        SecureRandom().nextBytes(it)
    }.joinToString("") {
        "%02x".format(it)
    }
}
