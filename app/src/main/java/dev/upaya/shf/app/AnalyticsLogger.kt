package dev.upaya.shf.app

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import dev.upaya.shf.data.labels.SHFLabel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AnalyticsLogger @Inject constructor() {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    fun logSessionStart() {
        firebaseAnalytics.logEvent("noting_session_start") { }
    }

    fun logNotingEvent(label: SHFLabel) {
        firebaseAnalytics.logEvent("noting_event") {
            param("label", label.name)
        }
    }
}
