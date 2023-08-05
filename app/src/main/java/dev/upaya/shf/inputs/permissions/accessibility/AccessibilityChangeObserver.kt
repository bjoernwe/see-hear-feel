package dev.upaya.shf.inputs.permissions.accessibility

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings


class AccessibilityChangeObserver private constructor(
    private val onAccessibilityChange: (Boolean) -> Unit,
    handler: Handler,
) : ContentObserver(handler) {

    companion object {

        private val uri = Settings.Secure.getUriFor(Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)

        fun getRegisteredObserver(
            context: Context,
            onAccessibilityChange: (Boolean) -> Unit,
            handler: Handler = Handler(Looper.getMainLooper()),
        ) : AccessibilityChangeObserver {
            return AccessibilityChangeObserver(
                onAccessibilityChange = onAccessibilityChange,
                handler = handler,
            ).apply {
                context.contentResolver.registerContentObserver(uri, false, this)
            }
        }

    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        onAccessibilityChange(selfChange)
    }

}
