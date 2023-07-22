package dev.upaya.shf.background

import android.accessibilityservice.AccessibilityService
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.inputs.BackgroundInputKeySource
import timber.log.Timber
import javax.inject.Inject


/**
 * An AccessibilityService for receiving input keys even when app is stopped (e.g., behind lock
 * screen).
 */
@AndroidEntryPoint
class BackgroundKeyEventService : AccessibilityService() {

    @Inject
    lateinit var inputKeySource: BackgroundInputKeySource

    override fun onKeyEvent(keyEvent: KeyEvent?): Boolean {

        if (keyEvent == null)
            return false

        Timber.d("Key pressed via accessibility is: $keyEvent")

        when (keyEvent.action) {
            KeyEvent.ACTION_DOWN -> {
                return inputKeySource.registerKeyDown(keyCode = keyEvent.keyCode)
            }
            KeyEvent.ACTION_UP -> {
                return inputKeySource.registerKeyDown(keyCode = keyEvent.keyCode)
            }
            else -> {}
        }

        return false
    }

    override fun onServiceConnected() {
        Timber.i("Service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Timber.i("Received event: $event")
    }
    override fun onInterrupt() {}
}
