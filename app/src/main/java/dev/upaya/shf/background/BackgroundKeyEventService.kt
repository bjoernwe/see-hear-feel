package dev.upaya.shf.background

import android.accessibilityservice.AccessibilityService
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import dagger.hilt.android.AndroidEntryPoint
import dev.upaya.shf.inputs.InputKeySource
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class BackgroundKeyEventService : AccessibilityService() {

    @Inject
    lateinit var inputKeySource: InputKeySource

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
