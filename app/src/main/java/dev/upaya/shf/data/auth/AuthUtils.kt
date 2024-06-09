package dev.upaya.shf.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialOption
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import timber.log.Timber
import java.security.SecureRandom


// This client ID can be found in the Cloud Console under API -> Credentials and there it is the
// client ID of a web application
const val WEB_CLIENT_ID = "993544001537-l5l3ehg0j5h7jus693fhqvmq5smiu88g.apps.googleusercontent.com"


internal suspend fun signInWithGoogle(activityContext: Context): GoogleIdTokenCredential? {
    return signInWithGoogleAutomatically(activityContext)
        ?: signInWithSelectedGoogleAccount(activityContext)
}


private suspend fun signInWithGoogleAutomatically(activityContext: Context) : GoogleIdTokenCredential? {
    val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(true)
        .setServerClientId(WEB_CLIENT_ID)
        .setAutoSelectEnabled(true)
        .setNonce(generateNonce())
        .build()
    return signInWithCredentialManager(googleIdOption, activityContext)
}


private suspend fun signInWithSelectedGoogleAccount(activityContext: Context): GoogleIdTokenCredential? {
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
