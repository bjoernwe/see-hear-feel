package dev.upaya.shf.utils

import dev.upaya.shf.SHFActivity
import dev.upaya.shf.background.notifications.startBackgroundNotificationService
import dev.upaya.shf.background.notifications.stopBackgroundNotificationService


/**
 * Start the user interaction that's happening during a session. In particular the parts that
 * are kept out of other parts of the architecture because they depend on Android libraries
 * and/or lifecycle-dependent context. Like foreground-service notifications and vibrations.
 */
internal fun SHFActivity.startUserInteractionForSession() {
    if (preferenceSource.isLockScreenSessionEnabled.value)
        startBackgroundUserInteractionForSession()
    else
        startForegroundUserInteractionForSession()
}

internal fun SHFActivity.stopUserInteractionForSession() {
    stopBackgroundNotificationService()
    eventVibrator.stopVibrator()
}

private fun SHFActivity.startBackgroundUserInteractionForSession() {
    startBackgroundNotificationService()
}

private fun SHFActivity.startForegroundUserInteractionForSession() {
    eventVibrator.startVibrator()
}
