package dev.upaya.shf.background.settings

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object AccessibilitySettingModule {

    @AccessibilitySetting
    @Provides
    fun providesAccessibilitySetting(
        @ApplicationContext appContext: Context,
    ): IBooleanSource {
        return AccessibilitySettingSource(
            appContext = appContext,
        )
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AccessibilitySetting
