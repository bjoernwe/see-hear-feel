package dev.upaya.shf.inputs.permissions.accessibility

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.upaya.shf.inputs.permissions.IBooleanSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessibilityPermissionSource @Inject constructor(
    @ApplicationContext appContext: Context,
) : IBooleanSource {

    private val _backgroundServiceIsAvailable = MutableStateFlow(getAccessibilityServiceAvailability(context = appContext))
    override val isEnabled: StateFlow<Boolean> = _backgroundServiceIsAvailable

    init {

        /**
         * Observe all changes made to Android's accessibility services, i.e., which ones are
         * currently (in)active.
         */
        AccessibilityChangeObserver.getRegisteredObserver(
            context = appContext,
            onAccessibilityChange = {
                _backgroundServiceIsAvailable.value = getAccessibilityServiceAvailability(context = appContext)
            },
        )

    }

}
