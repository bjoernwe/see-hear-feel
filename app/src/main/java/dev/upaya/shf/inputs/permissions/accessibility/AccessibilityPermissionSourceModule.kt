package dev.upaya.shf.inputs.permissions.accessibility

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.upaya.shf.inputs.permissions.IBooleanSource
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object AccessibilitySettingModule {

    @AccessibilitySetting
    @Provides
    fun providesAccessibilitySetting(
        @ApplicationContext appContext: Context,
    ): IBooleanSource {
        return AccessibilityPermissionSource(
            appContext = appContext,
        )
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AccessibilitySetting
