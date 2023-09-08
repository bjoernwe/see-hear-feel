package dev.upaya.shf.app

import android.accessibilityservice.AccessibilityService
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.data.UserInteractionRepository
import timber.log.Timber
import javax.inject.Inject


/**
 * An AccessibilityService for receiving input keys even when app is stopped (e.g., behind lock
 * screen).
 */
@AndroidEntryPoint
class KeyLoggingService : AccessibilityService() {

    @Inject
    lateinit var userInteractionRepository: UserInteractionRepository

    override fun onKeyEvent(keyEvent: KeyEvent?): Boolean {

        if (keyEvent == null)
            return false

        Timber.d("Key pressed via accessibility is: $keyEvent")

        when (keyEvent.action) {
            KeyEvent.ACTION_DOWN -> {
                return userInteractionRepository.registerKeyDownFromBackground(keyCode = keyEvent.keyCode)
            }
            KeyEvent.ACTION_UP -> {
                return userInteractionRepository.registerKeyUpFromBackground(keyCode = keyEvent.keyCode)
            }
            else -> {}
        }

        return false
    }

    override fun onServiceConnected() {
        Timber.i("BackgroundKeyEventService connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Timber.i("Received event: $event")
    }

    override fun onInterrupt() {}
}
