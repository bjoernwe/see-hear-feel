package dev.upaya.shf.app

import com.google.firebase.installations.FirebaseInstallations
import timber.log.Timber


// Knowing the Firebase Installation ID is useful for rolling out a remote config to a specific device
internal fun logFirebaseInstallationId() {
    FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Timber.tag("Installations").d("Installation ID: " + task.result)
        } else {
            Timber.tag("Installations").e("Unable to get Installation ID")
        }
    }
}
