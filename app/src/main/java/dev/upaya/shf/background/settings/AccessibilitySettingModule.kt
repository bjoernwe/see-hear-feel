package dev.upaya.shf.background.settings

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.upaya.shf.inputs.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object AccessibilitySettingModule {

    @AccessibilitySetting
    @Provides
    fun providesAccessibilitySetting(
        @ApplicationContext appContext: Context,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): IBooleanSource {
        return AccessibilitySettingSource(
            appContext = appContext,
            dispatcher = dispatcher,
        )
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AccessibilitySetting
