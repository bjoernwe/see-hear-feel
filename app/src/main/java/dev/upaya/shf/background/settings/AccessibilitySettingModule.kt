package dev.upaya.shf.background.settings

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object AccessibilitySettingModule {

    @AccessibilitySetting
    @Provides
    fun providesAccessibilitySetting(): IBooleanSource {
        return AccessibilitySettingSource()
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AccessibilitySetting
